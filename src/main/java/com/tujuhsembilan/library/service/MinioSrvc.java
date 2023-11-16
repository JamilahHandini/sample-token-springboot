package com.tujuhsembilan.library.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tujuhsembilan.library.abstraction.exception.MinioServiceException;
import com.tujuhsembilan.library.utility.TimeUtil;
import com.tujuhsembilan.library.utility.TimeUtil.TimeDomain;
import com.tujuhsembilan.template.components.MessageUtilComponent;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MinioSrvc {

  private static final Long DEFAULT_EXPIRY = TimeUtil.convert(TimeDomain.builder().hours(1).build()).toSeconds();

  private static final String GET_ERROR_CODE = "system.generic.error";
  private static final String PUT_ERROR_CODE = "system.generic.error";

  private final MinioClient minio;

  private final MessageUtilComponent message;

  private String getLink(String bucket, String filename, Long expiry) {
    try {
      return minio.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .method(Method.GET)
              .bucket(bucket)
              .object(filename)
              .expiry(Math.toIntExact(expiry), TimeUnit.SECONDS)
              .build());
    } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
        | InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
        | IllegalArgumentException | IOException e) {
      log.error(message.getMessage(GET_ERROR_CODE), e);
      throw new MinioServiceException(message.getMessage(GET_ERROR_CODE), e);
    }
  }


  public String getURL(String bucket, String filename, Long expiry) {
    try {
      return minio.getPresignedObjectUrl(
              GetPresignedObjectUrlArgs.builder()
                      .method(Method.GET)
                      .bucket(bucket)
                      .object(filename)
                      .expiry(Math.toIntExact(expiry), TimeUnit.SECONDS)
                      .build());
    } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
             | InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
             | IllegalArgumentException | IOException e) {
      log.error(message.getMessage(GET_ERROR_CODE), e);
      throw new MinioServiceException(message.getMessage(GET_ERROR_CODE), e);
    }
  }

  @Data
  public static class ListItem {
    private String objectName;
    private Long size;
    private boolean dir;
    private String versionId;

    @JsonIgnore
    private Item item;

    public ListItem(Item item) {
      this.objectName = item.objectName();
      this.size = item.size();
      this.dir = item.isDir();
      this.versionId = item.versionId();
      this.item = item;
    }
  }

  public List<Object> getList(String bucket) {
    List<Result<Item>> results = new ArrayList<>();
    minio.listObjects(
        ListObjectsArgs.builder()
            .bucket(bucket)
            .recursive(true)
            .build())
        .forEach(results::add);
    return results.stream().map(t -> {
      try {
        return new ListItem(t.get());
      } catch (InvalidKeyException | ErrorResponseException | IllegalArgumentException | InsufficientDataException
          | InternalException | InvalidResponseException | NoSuchAlgorithmException | ServerException
          | XmlParserException | IOException e) {
        log.error(message.getMessage(GET_ERROR_CODE), e);
        return null;
      }
    }).collect(Collectors.toList());
  }

  public void view(HttpServletResponse response, String bucket, String filename, Long expiry) {
    try {
      response.sendRedirect(this.getLink(bucket, filename, expiry));
    } catch (IOException e) {
      log.error(message.getMessage(GET_ERROR_CODE), e);
      throw new MinioServiceException(message.getMessage(GET_ERROR_CODE), e);
    }
  }

  public void view(HttpServletResponse response, String bucket, String filename) {
    this.view(response, bucket, filename, DEFAULT_EXPIRY);
  }

  @Data
  @Builder
  public static class UploadOption {
    private String filename;
  }

  public void upload(MultipartFile file, String bucket, Function<MultipartFile, UploadOption> modifier) {
    try {
      UploadOption opt = modifier.apply(file);

      minio.putObject(
          PutObjectArgs.builder()
              .bucket(bucket)
              .object(opt.filename)
              .stream(file.getInputStream(), file.getSize(), -1)
              .contentType(file.getContentType())
              .build());
    } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
        | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
        | IllegalArgumentException | IOException e) {
      log.error(message.getMessage(PUT_ERROR_CODE), e);
      throw new MinioServiceException(message.getMessage(PUT_ERROR_CODE), e);
    }
  }

  public void upload(MultipartFile file, String bucket) {
    this.upload(file, bucket,
        o -> UploadOption.builder()
            .filename(System.currentTimeMillis() + "_-_"
                + o.getOriginalFilename().replace(" ", "_"))
            .build());
  }

  public void uploadFile(MultipartFile file, String bucket) {
    this.upload(file, bucket,
            o -> UploadOption.builder()
                    .filename(o.getOriginalFilename().replace(" ", "_"))
                    .build());
  }

  public InputStream read(String filename, String bucket) throws InvalidKeyException, ErrorResponseException,
          InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException,
          XmlParserException, IllegalArgumentException, IOException {
    return minio.getObject(GetObjectArgs.builder()
            .bucket(bucket)
            .object(filename)
            .build());
  }

}