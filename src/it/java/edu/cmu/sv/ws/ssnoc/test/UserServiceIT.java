package edu.cmu.sv.ws.ssnoc.test;

import static com.eclipsesource.restfuse.Assert.assertBadRequest;
import static com.eclipsesource.restfuse.Assert.assertNotFound;
import static com.eclipsesource.restfuse.Assert.assertOk;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class UserServiceIT {
	@Rule
	public Destination destination = new Destination(this,
			"http://localhost:1234/ssnoc/");

	@Context
	public Response response;

	@HttpTest(method = Method.GET, path = "/users")
	public void testUsersFound() {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/user/hakan1", type = MediaType.APPLICATION_JSON)
	public void testInvalidLogin() {
//		assertOk(response);
        assertNotFound(response);
		//String messg = response.getBody();
		//Assert.assertEquals("User name not found", messg);
	}
}