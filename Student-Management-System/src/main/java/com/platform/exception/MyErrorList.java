package com.platform.exception;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyErrorList {

	private LocalDateTime localDateTime;
	private String details;
	private String message;
}
