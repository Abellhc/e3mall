package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;
/**
 * 图片上传下载controller
 * @author Abel
 *
 */
@Controller
public class PictureController {

	@Value(value="${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String fileUpload(MultipartFile uploadFile) {
		try {
		//1.取文件名
		String fileName = uploadFile.getOriginalFilename();
		//2.取文件扩展名
		String extName = fileName.substring(fileName.lastIndexOf(".")+1);
		//3.创建一份fastDFS
		FastDFSClient fastDFSClient;
			fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
		//4.执行上传
		String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
		//5.拼接返回的url和ip地址
		String url = IMAGE_SERVER_URL + path;
		System.out.println(url);
		//6.返回map
		Map result = new HashMap<>();
		result.put("error", 0);
		result.put("url", url);
		String json = JsonUtils.objectToJson(result);
		
		return json;
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			String json = JsonUtils.objectToJson(result);
			return json;
		}
	}
}
