package spring.boot.rest.validation.client.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import javassist.NotFoundException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler{

	public boolean hasError(ClientHttpResponse httpResponse) throws IOException{
		return (httpResponse.getStatusCode().series() == Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if(httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
			//handle server error
			System.out.println("Server error has happened");
		}else if(httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
			//handle client error
			System.out.println("Client error has happened");
			if(httpResponse.getStatusCode() == HttpStatus.NOT_FOUND){
				try{
					System.out.println("Response not found error has happened");
					throw new NotFoundException(null);
				}catch(NotFoundException n){
					n.printStackTrace();
				}
			}
		}
	}
}
