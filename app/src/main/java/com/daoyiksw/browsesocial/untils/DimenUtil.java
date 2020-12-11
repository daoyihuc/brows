package com.daoyiksw.browsesocial.untils;


import java.math.BigDecimal;

/**
 * AUTHOR : 谢明峰
 * TODO : 换算工具类
 * DATE : 2020/3/12
 * VERSION : 1.0
 */
public class DimenUtil {

    public static int parseInt(String value) {

        if (value != null && !"".equals(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public static int parseInt(String value, int defaultValue) {
        if (value != null && !"".equals(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static long parseLong(String value) {

        if (value != null && !"".equals(value)) {
            try {
                if (value.contains("."))
                    value = value.substring(0, value.indexOf("."));
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0L;
    }

    public static double parseDouble(String value) {

        if (value != null && !"".equals(value)) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0L;
    }


    public static float parseFloat(String value) {

        if (value != null && !"".equals(value)) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0f;
    }

    public static float parseShort(String value) {

        if (value != null && !"".equals(value)) {
            try {
                return Short.parseShort(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public static boolean parseBoolean(String value) {

        if (value != null && !"".equals(value)) {
            try {
                return Boolean.parseBoolean(value);
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static boolean isEqualsFloat(float value1, float value2) {

        if (Math.abs(value1 - value2) <= 1e-6) {
            return true;
        }
        return false;
    }

    /**
     * 将百度坐标转变成火星坐标
     *
     * @param lngLat_bd 百度坐标（百度地图坐标）
     * @return 火星坐标(高德、腾讯地图等)
     */
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
//    public static LatLng baidu2Gaode(LatLng lngLat_bd) {
//        double x = lngLat_bd.longitude - 0.0065, y = lngLat_bd.latitude - 0.006;
//        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
//        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
//        return new LatLng(dataDigit(6, z * Math.sin(theta)), dataDigit(6, z * Math.cos(theta)));
//    }

    /**
     * 对double类型数据保留小数点后多少位
     * 高德地图转码返回的就是 小数点后6位，为了统一封装一下
     * @param digit 位数
     * @param in    填写
     * @return 保留小数位后的数
     */
    static double dataDigit(int digit, double in) {
        return new BigDecimal(in).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

    }
}
