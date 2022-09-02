package org.oauth;

import org.Pojo.AppResponse;
import org.Pojo.webAutomation;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class Courses {
	
	@Test
	public void accesstoken(){
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiv8fTtJMOPYzS4BJvaduM6yH_HhkR9JbZS6U5wJEUlOQIoIM6DEl0_46_Rs1SeQw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
	String[] s  = url.split("code=");
	String[]s1 = s[1].split("&scope=");

		String tokenresponse = given().log().all().
		queryParam("code",s1[0])
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code").urlEncodingEnabled(false)
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath j = new JsonPath(tokenresponse);
		String token = j.get("access_token");
		AppResponse response = given().log().all().queryParam("access_token", token).header("Content-Type","application/json")
				.expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php")
				.then().assertThat().statusCode(200).extract().response().as(AppResponse.class);
				
				System.out.println(response.getInstructor());
				
		org.Pojo.Courses1 c= response.getCourses();
		
		List<webAutomation> l = c.getWebAutomation();
		
		for (int i =0; i<l.size();i++){
		
		System.out.println(l.get(i).getCourseTitle());
				
		}
		/*
		
		
		
		JsonPath j1 = new JsonPath(response);
		
		int count = j1.get("courses.webAutomation.size()");
		
		for(int i = 0; i< count; i++){
			
			String countTitle = j1.get("courses.webAutomation["+i+"].courseTitle");
			System.out.println(countTitle);
			
			String price = j1.get("courses.webAutomation["+i+"].price");
			System.out.println(price);
		}
	
		int count2 = j1.get("courses.api.size()");
		
		for(int i = 0; i< count2; i++){
			
			String countTitle = j1.get("courses.api["+i+"].courseTitle");
			System.out.println(countTitle);
			
			String price = j1.get("courses.api["+i+"].price");
			System.out.println(price);
		}
		
		int count3 = j1.get("courses.mobile.size()");
		
		for(int i = 0; i< count3; i++){
			
			String countTitle = j1.get("courses.mobile["+i+"].courseTitle");
			System.out.println(countTitle);
			
			String price = j1.get("courses.mobile["+i+"].price");
			System.out.println(price);
		}	*/
		
	}
	
}