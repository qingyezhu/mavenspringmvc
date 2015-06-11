package com.wangzhu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthCodeController {

	@RequestMapping("/authcode")
	@ResponseBody
	public void authCode(HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");

		BufferedImage image = new BufferedImage(60, 20,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		this.drawBg(graphics, 60, 20);

		this.draw(graphics);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		ServletOutputStream out = response.getOutputStream();
		out.write(buf);
		out.flush();
		bos.close();
		out.close();
	}

	private void drawBg(Graphics graphics, int width, int height) {
		graphics.setColor(new Color(0xDCDCDC));
		graphics.fillRect(0, 0, width, height);
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);

			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			graphics.setColor(new Color(red, green, blue));
			graphics.drawOval(x, y, 1, 0);
		}
	}

	Random random = new Random();

	private void draw(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		graphics.drawString("" + random.nextInt(10), 1, 17);
		graphics.drawString("" + random.nextInt(10), 16, 15);
		graphics.drawString("" + random.nextInt(10), 31, 18);
		graphics.drawString("" + random.nextInt(10), 46, 16);
	}
}
