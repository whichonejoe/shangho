package com.shangho.test.object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.shangho.blackcore.api.object.request.DeleteObjectCategoryRequest;
import com.shangho.blackcore.api.object.request.InsertObjectCategoryRequest;
import com.shangho.blackcore.api.object.request.ListObjectCategoryRequest;
import com.shangho.blackcore.api.object.request.UpdateObjectCategoryRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class ObjectCategoryTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testList() {
		List<String> list = new ArrayList<String>();
		list.add("4");
		ListObjectCategoryRequest request = new ListObjectCategoryRequest("token", "0", "small", list, "DESC");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/list");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {

		InsertObjectCategoryRequest request = new InsertObjectCategoryRequest("token", "namee43", "0", "small", 50,
				null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/insert");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFailInsert() {
		InsertObjectCategoryRequest request = new InsertObjectCategoryRequest("token", "name3", "1", "s", 50, "de3");
		// InsertObjectCategoryRequest request = new
		// InsertObjectCategoryRequest("token", null, null, null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/insert");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		UpdateObjectCategoryRequest request = new UpdateObjectCategoryRequest("token", 4, "name4", "0", "small", 30,
				"de4");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFailUpdate() {
		UpdateObjectCategoryRequest request = new UpdateObjectCategoryRequest("token", 4, "name4", "0", null, 30,
				"de4");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotExistedIDUpdate() {
		UpdateObjectCategoryRequest request = new UpdateObjectCategoryRequest("token", 114, "name4", "0", "big", 30,
				"de4");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		DeleteObjectCategoryRequest request = new DeleteObjectCategoryRequest("token", 4);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/category/delete");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
