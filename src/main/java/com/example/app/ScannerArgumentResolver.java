package com.example.app;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ScannerArgumentResolver implements ArgumentResolver {

	@Override
	public Argument resolve(InputStream stream) {
		Scanner sc = new Scanner(stream);
		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();
		return new Argument(a, b);
	}

}
