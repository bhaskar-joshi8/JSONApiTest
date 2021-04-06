package gluecode;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class JSONBinstepdef 
{

	APIMethodCalls strAPImethodcall = new APIMethodCalls();
	
	@Given("^I want to run a get method \"([^\"]*)\"$")
	public void i_want_to_run_a_get_method(String strBinID) throws Throwable 
	{
		strAPImethodcall.GETRequest(strBinID);
	}

	@Then("^I validate the outcomes of the return with \"([^\"]*)\"$")
	public void i_validate_the_outcomes_of_the_return_with(String strTextValue) throws Throwable 
	{
		strAPImethodcall.VerifyResponseText(strTextValue);
	}
	
	String POST_PARAMS_Key = "sample";
	String POST_PARAMS_Value = "Hello World";
	
	@Given("^I want to run a post method$")
	public void i_want_to_run_a_post_method() throws Throwable 
	{
		strAPImethodcall.POSTRequest("{\""+POST_PARAMS_Key+"\": \""+POST_PARAMS_Value+"\"}");
	}
	
	@Given("^I want to run a post method with sample value as \"([^\"]*)\"$")
	public void i_want_to_run_a_post_method_with_sample_value_as(String strParamValue) throws Throwable 
	{
		strAPImethodcall.POSTRequest("{\""+POST_PARAMS_Key+"\": \""+strParamValue+"\"}");
	}
	
	@Given("^I want to run a put method with sample value as \"([^\"]*)\" for binid \"([^\"]*)\"$")
	public void i_want_to_run_a_put_method_with_sample_value_as_for_binid(String strParamValue, String strBinId) throws Throwable 
	{
		strAPImethodcall.PUTRequest("{\""+POST_PARAMS_Key+"\": \""+strParamValue+"\"}", strBinId);
	}

	@Given("^I want to run a delete method for binid \"([^\"]*)\"$")
	public void i_want_to_run_a_delete_method_for_binid(String strBinID) throws Throwable 
	{
		strAPImethodcall.DELETERequest(strBinID);
	}

}
