package com.tujuhsembilan.library;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import com.tujuhsembilan.library.abstraction.enumeration.L10n;
import com.tujuhsembilan.library.utility.CipherUtil;
import com.tujuhsembilan.library.utility.CurrencyUtil;
import com.tujuhsembilan.library.utility.HashUtil;
import com.tujuhsembilan.library.utility.TimeUtil;
import com.tujuhsembilan.library.utility.TimeUtil.TimeDomain;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

public class LibraryTests {

	@Test
	void timeUtilityTest() {
		var e = 3666L;
		var a = TimeUtil.convert(TimeDomain.builder().hours(1).minutes(1).seconds(5).millis(1000).build()).toSeconds();
		assertEquals(e, a, "time converter utility test");
	}

	@Test
	void currencyUtilityTest() {
		assertEquals("Rp123.456.789,25", CurrencyUtil.of(L10n.ID).withValue(123456789.2468).asCurrencyString());
		assertEquals("$123,456,789.25", CurrencyUtil.of(L10n.US).withValue(123456789.2468).asCurrencyString());
		assertEquals("Rp123.456.789,25",
				CurrencyUtil.of(L10n.ID).withValue(BigDecimal.valueOf(123456789.2468)).asCurrencyString());
		assertEquals("$123,456,789.25",
				CurrencyUtil.of(L10n.US).withValue(BigDecimal.valueOf(123456789.2468)).asCurrencyString());
		assertEquals("ten Indonesian Rupiah", CurrencyUtil.of(L10n.ID).withValue(BigDecimal.valueOf(10)).spell());
		assertEquals("sepuluh Dolar Amerika Serikat",
				CurrencyUtil.of(L10n.US).withValue(BigDecimal.valueOf(10)).spell(L10n.ID));
	}

	@Test
	void sha256UtilityTest() throws DecoderException {
		String toHashString = "HASH-TEST";
		byte[] toHashByte = toHashString.getBytes();

		String hexRes = "b502b4c61f1742dd5e98bc7428b64fd03510f7c9556802c3a069ee455c72e172";
		String b64Res = "tQK0xh8XQt1emLx0KLZP0DUQ98lVaALDoGnuRVxy4XI=";

		byte[] resultHexByte = Hex.decodeHex(hexRes);

		assertArrayEquals(resultHexByte, HashUtil.SHA_256.digest(toHashString));
		assertArrayEquals(resultHexByte, HashUtil.SHA_256.digest(toHashByte));
		assertEquals(hexRes, HashUtil.SHA_256.digestAsHex(toHashString));
		assertEquals(hexRes, HashUtil.SHA_256.digestAsHex(toHashByte));
		assertEquals(b64Res, new String(HashUtil.SHA_256.digestAsBase64(toHashString)));
		assertEquals(b64Res, new String(HashUtil.SHA_256.digestAsBase64(toHashByte)));
		assertEquals(b64Res, HashUtil.SHA_256.digestAsBase64String(toHashString));
		assertEquals(b64Res, HashUtil.SHA_256.digestAsBase64String(toHashByte));
	}

	@Test
	void sha512UtilityTest() throws DecoderException {
		String toHashString = "HASH-TEST";
		byte[] toHashByte = toHashString.getBytes();

		String hexRes = "bcdf1a950af541c2bdeb3e1188a917795bdc795950a06853ab3e1a844a815051062854a2696cba09dc18bd4779e63a1c36c516d635ab07f183da090e2acab75a";
		String b64Res = "vN8alQr1QcK96z4RiKkXeVvceVlQoGhTqz4ahEqBUFEGKFSiaWy6CdwYvUd55jocNsUW1jWrB/GD2gkOKsq3Wg==";

		byte[] resultHexByte = Hex.decodeHex(hexRes);

		assertArrayEquals(resultHexByte, HashUtil.SHA_512.digest(toHashString));
		assertArrayEquals(resultHexByte, HashUtil.SHA_512.digest(toHashByte));
		assertEquals(hexRes, HashUtil.SHA_512.digestAsHex(toHashString));
		assertEquals(hexRes, HashUtil.SHA_512.digestAsHex(toHashByte));
		assertEquals(b64Res, new String(HashUtil.SHA_512.digestAsBase64(toHashString)));
		assertEquals(b64Res, new String(HashUtil.SHA_512.digestAsBase64(toHashByte)));
		assertEquals(b64Res, HashUtil.SHA_512.digestAsBase64String(toHashString));
		assertEquals(b64Res, HashUtil.SHA_512.digestAsBase64String(toHashByte));
	}

	@Test
	void sha3_256UtilityTest() throws DecoderException {
		String toHashString = "HASH-TEST";
		byte[] toHashByte = toHashString.getBytes();

		String hexRes = "84041d897a797b927105ae7920b70b540d009a4278b4775927e27e18c1da16e4";
		String b64Res = "hAQdiXp5e5JxBa55ILcLVA0AmkJ4tHdZJ+J+GMHaFuQ=";

		byte[] resultHexByte = Hex.decodeHex(hexRes);

		assertArrayEquals(resultHexByte, HashUtil.SHA3_256.digest(toHashString));
		assertArrayEquals(resultHexByte, HashUtil.SHA3_256.digest(toHashByte));
		assertEquals(hexRes, HashUtil.SHA3_256.digestAsHex(toHashString));
		assertEquals(hexRes, HashUtil.SHA3_256.digestAsHex(toHashByte));
		assertEquals(b64Res, new String(HashUtil.SHA3_256.digestAsBase64(toHashString)));
		assertEquals(b64Res, new String(HashUtil.SHA3_256.digestAsBase64(toHashByte)));
		assertEquals(b64Res, HashUtil.SHA3_256.digestAsBase64String(toHashString));
		assertEquals(b64Res, HashUtil.SHA3_256.digestAsBase64String(toHashByte));
	}

	@Test
	void sha3_512UtilityTest() throws DecoderException {
		String toHashString = "HASH-TEST";
		byte[] toHashByte = toHashString.getBytes();

		String hexRes = "6351b0ab3f7013f66f8b4237d9c7bf9ca7733fbebff28071441a851dba85dfc7c15c4d4b9dccdb779c1d41f635f7848534b9c68fa135ea77aeef0fe7997b8e35";
		String b64Res = "Y1Gwqz9wE/Zvi0I32ce/nKdzP76/8oBxRBqFHbqF38fBXE1Lnczbd5wdQfY194SFNLnGj6E16neu7w/nmXuONQ==";

		byte[] resultHexByte = Hex.decodeHex(hexRes);

		assertArrayEquals(resultHexByte, HashUtil.SHA3_512.digest(toHashString));
		assertArrayEquals(resultHexByte, HashUtil.SHA3_512.digest(toHashByte));
		assertEquals(hexRes, HashUtil.SHA3_512.digestAsHex(toHashString));
		assertEquals(hexRes, HashUtil.SHA3_512.digestAsHex(toHashByte));
		assertEquals(b64Res, new String(HashUtil.SHA3_512.digestAsBase64(toHashString)));
		assertEquals(b64Res, new String(HashUtil.SHA3_512.digestAsBase64(toHashByte)));
		assertEquals(b64Res, HashUtil.SHA3_512.digestAsBase64String(toHashString));
		assertEquals(b64Res, HashUtil.SHA3_512.digestAsBase64String(toHashByte));
	}

	@Test
	void md5UtilityTest() throws DecoderException {
		String toHashString = "HASH-TEST";
		byte[] toHashByte = toHashString.getBytes();

		String hexRes = "31e54cc7e177b41f221e67038be1fc8e";
		String b64Res = "MeVMx+F3tB8iHmcDi+H8jg==";

		byte[] resultHexByte = Hex.decodeHex(hexRes);

		assertArrayEquals(resultHexByte, HashUtil.MD5.digest(toHashString));
		assertArrayEquals(resultHexByte, HashUtil.MD5.digest(toHashByte));
		assertEquals(hexRes, HashUtil.MD5.digestAsHex(toHashString));
		assertEquals(hexRes, HashUtil.MD5.digestAsHex(toHashByte));
		assertEquals(b64Res, new String(HashUtil.MD5.digestAsBase64(toHashString)));
		assertEquals(b64Res, new String(HashUtil.MD5.digestAsBase64(toHashByte)));
		assertEquals(b64Res, HashUtil.MD5.digestAsBase64String(toHashString));
		assertEquals(b64Res, HashUtil.MD5.digestAsBase64String(toHashByte));
	}

	@Test
	void cipherChaCha20Poly1305Test() throws Exception {
		var cipher = new CipherUtil.ChaCha20Poly1305(new SecretKeySpec(
				Arrays.copyOf(
						MessageDigest.getInstance("SHA-384").digest(
								"TEST".getBytes(StandardCharsets.UTF_8)),
						32),
				"ChaCha20-Poly1305"));

		var e = "TEST";
		var cipheredText = cipher.encrypt(e.getBytes(StandardCharsets.UTF_8));
		var a = new String(cipher.decrypt(cipheredText), StandardCharsets.UTF_8);
		assertEquals(e, a, "ChaCha20-Poly1305 crypto test");
	}

}