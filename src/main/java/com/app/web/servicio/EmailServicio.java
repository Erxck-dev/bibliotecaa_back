package com.app.web.servicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {

    private final JavaMailSender mailSender;

    public EmailServicio(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreoReserva(
            String correoAdmin,
            String emailUsuario,
            String libro) {

        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setTo(correoAdmin);
            helper.setSubject("📚 Nueva reserva de libro");

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            String html = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color:#f4f4f4; padding:20px;">
                    <div style="background:#ffffff; padding:20px; border-radius:8px;">
                        <h2 style="color:#2c3e50;">📚 Reserva de Libro</h2>
                        <hr>
                        <p><strong>Usuario:</strong> %s</p>
                        <p><strong>Libro:</strong> %s</p>
                        <p><strong>Fecha y hora:</strong> %s</p>
                        <br>
                        <p style="font-size:12px; color:#888;">
                            Sistema de Biblioteca
                        </p>
                    </div>
                </body>
                </html>
                """.formatted(
                        emailUsuario,
                        libro,
                        ahora.format(formato)
                );

            helper.setText(html, true);
            mailSender.send(mensaje);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
