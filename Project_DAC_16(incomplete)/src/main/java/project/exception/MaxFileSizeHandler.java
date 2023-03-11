package project.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class MaxFileSizeHandler 
{
	@Autowired
	private Environment env;
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> maxFileSizeExceded()
	{
		String maxSize= env.getProperty("spring.servlet.multipart.max-file-size");
		String message = "size exceeds "+maxSize;
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
