package cn.e3mall.fastDFS;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDFSTest {

	@Test
	public void testUpdateLoad() throws Exception{
		//1.加载配置文件 ， 配置文件中的内容就是tracker服务的地址
		ClientGlobal.init("E:/Develop/e3mall-pero/e3-manager-web/src/main/resources/conf/client.conf");
		//2.创建一个trackerclient对象  直接new一个
		TrackerClient trackerClient = new TrackerClient();
		//3.使用trackerclient对戏那个创建连接， 获得一个trackerserver对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//4.创建一个storageserver的引用， 值为null
		StorageServer storageServer = null;
		//5。创建一个storageclient对象， 需要两个参数trackerserver'对象 storageserver对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//6.使用storageclient对象上传图片
		//扩展名不带“.”
		String[] strings = storageClient.upload_file("C:/Users/Abel/Desktop/3.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDFSClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("E:/Develop/e3mall-pero/e3-manager-web/src/main/resources/conf/client.conf");
		String file = fastDFSClient.uploadFile("C:/Users/Abel/Desktop/4.jpg");
		System.out.println(file);
	}
}
