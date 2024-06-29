package io.github.dev.agussuhardi.iso8583.client.util;

import jakarta.xml.bind.DatatypeConverter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author agussuhardi
 * {@code @created} 5/14/24 :1:24 PM
 * {@code @project} svc-bni
 */
@Slf4j
public class Iso8583Util {

    @SneakyThrows
    public static void printISOMessage(ISOMsg isoMsg) {
        System.out.printf("MTI = %s%n", isoMsg.getMTI());
        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i)) {
                System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
            }
        }
    }

    public static String getAmount(BigDecimal amount) {
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        var amountStr = amount.toString().replace(".", "");
        return org.apache.commons.lang.StringUtils.leftPad(amountStr, 12, "0");
    }

    public static String getStan() {
        return String.format("%06d", System.currentTimeMillis() % 1000000);
    }

    public static byte[] getNii() {
        var nii = "001";
        return DatatypeConverter.parseHexBinary("600" + nii + "0000");
    }

    public static LocalDateTime getLocalDateTime() {
        var millis = System.currentTimeMillis();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    public static String unpackMessageToJson(ISOMsg isoMsg) {
        try {
            isoMsg.setPackager(new GenericPackager("cfg/packager.xml"));
            return new String(isoMsg.pack());
        } catch (ISOException e) {
            log.error("bad unpack iso message =>{}", e.getMessage());
            return "{}";
        }
    }
}
