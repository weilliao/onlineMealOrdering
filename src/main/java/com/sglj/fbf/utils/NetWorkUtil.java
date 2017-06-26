/**
 * 
 */
package com.sglj.fbf.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取机器网络参数帮助类
 * @author guanhongwei
 *
 */
public class NetWorkUtil {
	
	/**
	 * 10进制转换16进制
	 * @param b
	 * @return
	 */
	private static String parseByte(byte b) {
		String s = "00" + Integer.toHexString(b);
		return s.substring(s.length() - 2);
	}

	/**
	 * 获取本机所有网卡的MAC地址
	 * @return
	 */
	public static List<String> getLocalAllMacList() {
		List<String> list = new ArrayList<String>();
		try {
			//返回所有网络接口的一个枚举实例
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				// 获得当前网络接口
				NetworkInterface network = e.nextElement();
				if (network != null) {
					// 获得MAC地址
					if (network.getHardwareAddress() != null) {
						// 结果是一个byte数组，每项是一个byte，通过parseByte方法转换成常见的十六进制表示
						byte[] addres = network.getHardwareAddress();
						StringBuffer sb = new StringBuffer();
						if (addres != null && addres.length > 1) {
							for (int i = 0; i < addres.length; i++) {
								sb.append(parseByte(addres[i]));
								if(i != addres.length - 1){
									sb.append("-");
								}
							}
							
							list.add(sb.toString().toUpperCase());
						}
					}
				} else {
					throw new Exception("获取MAC地址发生异常");
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	/**
     * 获取本地IP列表（针对多网卡情况）
     * @return IPV4
     */
    public static List<String> getLocalAllIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }
	
	public static void main(String[] args) {
		System.err.println(NetWorkUtil.getLocalAllMacList());
		System.err.println(NetWorkUtil.getLocalAllIPList());
	}


	

}
