//package com.wangan.security.core;
//
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.SftpException;
//import org.apache.commons.lang.StringUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
////@Component
//public class SftpUtils {
////	@Autowired
////	private SFTPChannel sftp;
//
//	/**
//	 * 通过sftp获取到文件，下载远程文件到本地服务器相应目录下
//	 * @param file 目标文件（）
//	 * @param filePath 全路径，如：/home/vasuser/file/upload/20170414/20170414000573.png
//	 * @return
//	 */
//	public static boolean getFile(File file,String filePath){
//		SFTPChannel sftp = new SFTPChannel();
//    	ChannelSftp chansftp = null;
//		boolean flg = false;
//
//		int end = filePath.lastIndexOf("/")+1;
//		String imgPath = filePath.substring(0,end);
//		String fileName = filePath.substring(end,filePath.length());
//		File imgDirectory = new File(imgPath);
//		if(!imgDirectory.exists()){
//			if (!imgDirectory.mkdirs()) {
//				System.out.println("创建失败");
//			}
//		}
//		try {
//			if (!file.createNewFile()) {
//				System.out.println("创建失败");
//			}
//			chansftp = sftp.getChannel( 600);
//			chansftp.cd(imgPath);
//			//System.out.println(System.currentTimeMillis());
//			chansftp.get(fileName, imgPath);
//			//System.out.println(System.currentTimeMillis());
//			flg = true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (JSchException e) {
//			if (!file.delete()) {
//				System.out.println("删除失败");
//			}
//			//System.out.println("空文件已删除1");
//			e.printStackTrace();
//		} catch (SftpException e) {
//			if (!file.delete()) {
//				System.out.println("删除失败");
//			}
//			//System.out.println("空文件已删除2");
//			e.printStackTrace();
//		}  finally{
//			try {
//		        sftp.closeChannel();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//		return flg;
//	}
//
//
//    /**
//     * @description 上传文件
//     * @author qinj
//     * @date 2018/4/28 17:43
//     * @param destFile 要上传的文件
//     * @param filePath 上传文件路径
//     */
//    public static boolean uploadFile(File destFile, String filePath) {
//        SFTPChannel sftp = new SFTPChannel();
//        ChannelSftp channelSftp = null;
//        try {
//            channelSftp = sftp.getChannel( 60000);
//            boolean exist = existDir(channelSftp, filePath);
//            if(!exist)
//                channelSftp.mkdir(filePath);
//            channelSftp.put(new FileInputStream(destFile), filePath + "/" + destFile.getName());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  finally{
//            try {
//                sftp.closeChannel();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    public static boolean existDir(ChannelSftp channelSftp, String dir) {
//        if(StringUtils.isBlank(dir))
//            throw new RuntimeException("目录不能为空!");
//        try {
//            List list = channelSftp.ls(dir);
//            if(list == null || list.isEmpty())
//                return false;
//            return list.contains(dir);
//        } catch (SftpException e) {
//            return false;
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
////        File file = new File("/home/vasuser/file/upload/20180427/20180427007941.png");
////        boolean result = uploadFile(file, "/home/vasuser/file/upload/20180827");
////        System.out.println(result);
//		getFile(new File("/home/vasuser/file/upload/20180911/20180911010722.png"),"/home/vasuser/file/upload/20180911/20180911010722.png",6000,
//				"121.40.153.187",22,"vasuser","vasUSER~123");
//    }
//
//	/**
//	 * 上送阿里云
//	 * @param destFile
//	 * @param filePath
//	 * @param timeout
//	 * @param ftpHost
//	 * @param port
//	 * @param ftpUserName
//	 * @param ftpPassword
//	 * @return
//	 */
//	public boolean uploadFile(
//			File destFile,
//			String filePath,
//			int timeout,
//			String ftpHost,
//			int port,
//			String ftpUserName,
//			String ftpPassword) {
//		SFTPChannel sftp = new SFTPChannel();
//		ChannelSftp channelSftp = null;
//		try {
//			channelSftp = sftp.getChannel(timeout, ftpHost, port, ftpUserName, ftpPassword);
//			boolean exist = aliyunExistDir(channelSftp, filePath);
//			if (!exist) {
//				channelSftp.mkdir(filePath);
//			}
//			channelSftp.put(new FileInputStream(destFile), filePath + "/" + destFile.getName());
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				sftp.closeChannel();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//	public boolean aliyunExistDir(ChannelSftp channelSftp, String dir) {
//		if (StringUtils.isBlank(dir)) {
//			throw new RuntimeException("目录不能为空!");
//		}
//		try {
//			List list = channelSftp.ls(dir);
//			if (list == null || list.isEmpty()) {
//				return false;
//			} else {
//				return true;
//			}
//		} catch (SftpException e) {
//			return false;
//		}
//	}
//
//
//	/**
//	 * 通过sftp获取到文件，下载远程文件到本地服务器相应目录下
//	 * @param file 目标文件（）
//	 * @param filePath 全路径，如：/home/vasuser/file/upload/20170414/20170414000573.png
//	 * @return
//	 */
//	public static boolean getFile(	File file,
//									  String filePath,
//									  int timeout,
//									  String ftpHost,
//									  int port,
//									  String ftpUserName,
//									  String ftpPassword){
//
//		SFTPChannel sftp = new SFTPChannel();
//		ChannelSftp chansftp = null;
//		boolean flg = false;
//
//		int end = filePath.lastIndexOf("/")+1;
//		String imgPath = filePath.substring(0,end);
//		String fileName = filePath.substring(end,filePath.length());
//		File imgDirectory = new File(imgPath);
//		if(!imgDirectory.exists()){
//			if (!imgDirectory.mkdirs()) {
//				System.out.println("创建失败");
//			}
//		}
//		try {
//			if (!file.createNewFile()) {
//				System.out.println("创建失败");
//			}
//			chansftp = sftp.getChannel(timeout, ftpHost, port, ftpUserName, ftpPassword);
//			chansftp.cd(imgPath);
//			//System.out.println(System.currentTimeMillis());
//			chansftp.get(fileName, imgPath);
//			//System.out.println(System.currentTimeMillis());
//			flg = true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (JSchException e) {
//			if (!file.delete()) {
//				System.out.println("删除失败");
//			}
//			//System.out.println("空文件已删除1");
//			e.printStackTrace();
//		} catch (SftpException e) {
//			if (!file.delete()) {
//				System.out.println("删除失败");
//			}
//			//System.out.println("空文件已删除2");
//			e.printStackTrace();
//		}  finally{
//			try {
//				sftp.closeChannel();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return flg;
//	}
//
//}
