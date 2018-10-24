//package com.wangan.security.core;
//import com.jcraft.jsch.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Properties;
//
////import org.apache.log4j.Logger;
//
//
//public class SFTPChannel {
//    private Session session = null;
//    private Channel channel = null;
////    @Autowired
////    private SFTPConstants sftp;
//
//    private Logger log = LoggerFactory.getLogger(SFTPChannel.class);
//
//    public ChannelSftp getChannel(int timeout) throws JSchException {
//
//        String ftpHost = SFTPConstants.SFTP_REQ_HOST; //host
//        String port = SFTPConstants.SFTP_REQ_PORT;  //port 22
//        String ftpUserName = SFTPConstants.SFTP_REQ_USERNAME; //userName
//        String ftpPassword = SFTPConstants.SFTP_REQ_PASSWORD; //password
//
//        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT; //port 22
//        if (port != null && !port.equals("")) {
//            ftpPort = Integer.parseInt(port);
//        }
//
//        JSch jsch = new JSch(); // 创建JSch对象
//        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
//        log.info("Session created.");
//        if (ftpPassword != null) {
//            session.setPassword(ftpPassword); // 设置密码
//        }
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        session.setConfig(config); // 为Session对象设置properties
//        session.setTimeout(timeout); // 设置timeout时间 单位毫秒
//        session.connect(); // 通过Session建立链接
//        log.info("Session connected.");
//
//        log.info("Opening Channel.");
//        channel = session.openChannel("sftp"); // 打开SFTP通道
//        channel.connect(); // 建立SFTP通道的连接
//        log.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
//                + ", returning: " + channel);
//        return (ChannelSftp) channel;
//    }
//
//    public void closeChannel() throws Exception {
//        if (channel != null) {
//            channel.disconnect();
//        }
//        if (session != null) {
//            session.disconnect();
//        }
//    }
//
//    public ChannelSftp getChannel(int timeout,String ftpHost,int port,String ftpUserName,String ftpPassword) throws JSchException {
//        JSch jsch = new JSch(); // 创建JSch对象
//        session = jsch.getSession(ftpUserName, ftpHost, port); // 根据用户名，主机ip，端口获取一个Session对象
//        log.info("Session created.");
//        if (ftpPassword != null) {
//            session.setPassword(ftpPassword); // 设置密码
//        }
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        session.setConfig(config); // 为Session对象设置properties
//        session.setTimeout(timeout); // 设置timeout时间 单位毫秒
//        session.connect(); // 通过Session建立链接
//        log.info("Session connected.");
//
//        log.info("Opening Channel.");
//        channel = session.openChannel("sftp"); // 打开SFTP通道
//        channel.connect(); // 建立SFTP通道的连接
//        log.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
//                + ", returning: " + channel);
//        return (ChannelSftp) channel;
//    }
//}
