package nl.maxvandewiel.service.userregistration;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by max on 8/26/16.
 */
public class Test {

    public static void delme_main(String[] args) {
        int counter = 0;
        Map<Long,String> uuids = new HashMap<Long, String>();
        Long currentTimeInMilliseconds = System.currentTimeMillis();
        Long endTimeInMilliseconds = null;
        int xTimes = 100000;
        boolean error = false;
        while (counter <= xTimes) {
            counter++;
            System.out.println();
            System.out.println("Generating UUID From TimeStamp...");
            UUID uid = generateId();
            System.out.println("UUID: " + uid.toString() + "\nUUID Timestamp: " + uid.timestamp());

            /* 6 digit random salt that must not be greater than 650000 */
            System.out.println("Generating Salt...");
            Double salt = Math.floor(100000 + Math.random() * 900000);
            salt = (salt > 650000)?650000:salt;
            System.out.println("salt: " + salt.intValue());

            System.out.println("Generating 18 digit UUID from UUID with Salt...");
            String uUID = String.valueOf(uid.timestamp()*salt.intValue());
            System.out.println("UUID: " + uUID + "\nLength: " + uUID.length());

            System.out.println();

            if (uuids.get(uUID) != null) {
                System.out.println("###### ERROR :: INPROPER UUID GENERARION,..FOUND DOUBLURE :: fix Algorithm!!!! #####");
                error = true;
                break;
            }
            else if (uUID.length() != 18) {
                System.out.println("###### ERROR :: NOT 18 CHARS :: FIX THE FORMULA !!!! #####\n" +
                "Salt: " + salt + " UUID: " + uUID);
                error = true;
                break;
            }
            else {
                uuids.put(Long.valueOf(uUID),uUID);
            }
        }
        endTimeInMilliseconds = System.currentTimeMillis();
        System.out.println();
        if (error) {
            System.out.println("ERROR after handling " + uuids.size() + " out of " + xTimes + " generating Uniqueue UUID's based on timestamp with 18 digits in " + (endTimeInMilliseconds - currentTimeInMilliseconds) + " milliseconds.");
        }
        else {
            System.out.println("Handled " + xTimes + " Uniqueue UUID generations based on timestamp with 18 digits in " + (endTimeInMilliseconds - currentTimeInMilliseconds) + " milliseconds.");
        }
    }

    private static final Object lock = new Object();

    private static long lastTime;
    private static long clockSequence = 0;
    private static final long hostIdentifier = getHostId();

    /**
     * Will generate unique time based UUID where the next UUID is
     * always greater then the previous.
     */
    public final static UUID generateId() {
        return generateIdFromTimestamp(System.currentTimeMillis());
    }

    private final static UUID generateIdFromTimestamp(long currentTimeMillis){
        long time;

        synchronized (lock) {
            if (currentTimeMillis > lastTime) {
                lastTime = currentTimeMillis;
                clockSequence = 0;
            } else  {
                ++clockSequence;
            }
        }

        time = currentTimeMillis;

        // low Time
        time = currentTimeMillis << 32;

        // mid Time
        time |= ((currentTimeMillis & 0xFFFF00000000L) >> 16);

        // hi Time
        time |= 0x1000 | ((currentTimeMillis >> 48) & 0x0FFF);

        long clockSequenceHi = clockSequence;

        clockSequenceHi <<=48;

        long lsb = clockSequenceHi | hostIdentifier;

        return new UUID(time, lsb);
    }

    private static final long getHostId(){
        long  macAddressAsLong = 0;
        try {
            Random random = new Random();
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                random.nextBytes(mac); // we don't really want to reveal the actual MAC address
                //Converts array of unsigned bytes to an long
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        macAddressAsLong <<= 8;
                        macAddressAsLong ^= (long)mac[i] & 0xFF;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddressAsLong;
    }

    public static String formatDecimal(BigDecimal b, int max) {
        // trivial case
        String bs = b.stripTrailingZeros().toPlainString();
        if (bs.length() <= max) {
            return bs;
        }
        // determine the max integer = 1.0Emax
        String maxInteger = "1" + StringUtils.repeat("0", max - 1);
        // determine the min fraction = 1.0E-max
        String minFraction = "0." + StringUtils.repeat("0", max - 2) + "1";
        // get the integer part
        String integerPart = String.valueOf(b.intValue());
        // make the pattern like ###.### with the correct repetition
        String pattern = StringUtils.repeat("#", max - integerPart.length()) + "." + StringUtils.repeat("#", max - 1 - integerPart.length());
        // play with Message format, using a choice to determine when to use the exponential format
        MessageFormat fmt = new MessageFormat( //
                "{0,choice," + minFraction + "<{0,number,'0.#E0'}|0.1#{0,number,'" + pattern + "'}|" + maxInteger + "<{0,number,'0.#E0'}}" //
        );
        // time to format the number
        return fmt.format(new Object[] {b});
    }
}
