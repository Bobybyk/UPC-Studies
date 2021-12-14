package client;

public class ServerInfo {
    private static String name;
    private static String[] ip = new String[4]; 
    private static boolean ipFormat = false;
    private static String port;
    private static boolean portFormat = false;
   

    public static String getName() {
        return name;
    }
    public static String[] getIp() {
        return ip;
    }
    public static String getPort() {
        return port;
    }
    public static boolean getIpFormat() {
        return ipFormat;
    }
    public static boolean getPortFormat() {
        return portFormat;
    }

    public static void setName(String n) {
        name = n;
    }

    public static void setIp(String num) {
        String tmp = "";
        int del = 0;
        for (int i = 0 ; i<num.length() ; i++) {
            if (num.charAt(i) == '.') {
                del += 1;
                if (del > 3 || tmp.length() == 0) {
                    System.out.println("Wrong format for ip");
                    ipFormat = false;   
                    return;
                }
                ip[del-1] = tmp;
                tmp = "";
            }
            if (Character.isDigit(num.charAt(i))) {
                tmp += num.charAt(i);
            }
            if (!Character.isDigit(num.charAt(i)) && num.charAt(i) != '.') {
                System.out.println("Wrong format for ip");
                ipFormat = false;   
                return;   
            }
        } 
        ip[3] = tmp;
        ipFormat = true;
        for (int i = 0 ; i<ip.length ; i++) {
            if (ip[i] == null || ip[i].equals("")) {
                System.out.println("Wrong format for ip");
                ipFormat = false;
            }
        }

    }

    public static void setPort(String p) {
        for (int i = 0 ; i<p.length() ; i++) {
            if (!Character.isDigit(p.charAt(i))) {
                System.out.println("wrong format for port");
                ipFormat = false;
                return;
            }
        }
        port = p;
        portFormat = true;   
    }
}
