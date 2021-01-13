import java.io.IOException;
import java.net.*;
/*
 * 1.建立UDPSOCKET服务
 * 2. 提供数据， 并将数据封装到数据包
 * 3.通过SOCKET服务的发送功能， 发送数据包
 * 4.关闭资源
 * */
class UDPSend {
    public static void main(String[] args) throws IOException {
        //1.建立UDP服务， 通过DATAGRAMSOCKET()
        DatagramSocket ds = new DatagramSocket();

        //2.确定数据， 并封装成数据包，
        // DatagramPacket(byte[] buf, int length, InetAddress address, int port)
        byte[] buf = "udp ge men lai le".getBytes();
        DatagramPacket dp = new DatagramPacket(buf,buf.length,
                InetAddress.getByName("192.168.0.168"),10000);

        //3.通过SOCKET服务， 将已有的数据包发送出去， 通过SEND方法。
        ds.send(dp);

        //4.关闭资源
        ds.close();
    }
}



/*
 *定义一个应用程序，用于接受数据*
 * 定义UDP的接受端
 *
 * 1.定义UDPSOCKET服务， 通常会监听一个端口， 其实就是给这个接收网络应用程序定义数据标识。
 *
 * 2. 定义一个数据包， 因为要存储接受到的字节数据
 * 因为数据包对象中有更多功能可以接受提取字节数据中的不同数据信息
 * 3.通过SOCKET服务的RECEIVE方法接受数据,
 * 4.通过数据包对象的特殊功能， 将这些不同的数据取出， 打印在控制台上
 * 5.关闭资源
 */
class UDPRece{
    public static void main(String[] args) throws IOException {
        //1.创建UDP SOCET, 建立端点
        DatagramSocket ds = new DatagramSocket(10000);

        //2.定义数据包， 用于存储数据
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        //3.通过服务的RECEIVE方法将收到的数据存入数据包
        ds.receive(dp);

        //4.通过数据包的方法获取其中的数据
        String ip = dp.getAddress().getHostAddress();
        String data = new String(dp.getData(),0,dp.getLength());
        int port = dp.getPort();
        System.out.println(ip +"::" + data + "::"+port);

        //5.关闭资源
        ds.close();
    }
}
