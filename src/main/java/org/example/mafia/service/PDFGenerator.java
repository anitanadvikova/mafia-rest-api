package org.example.mafia.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PDFGenerator {

    public ByteArrayOutputStream generate(Map<String, Object> map) {
        byte[] data = parseThymeleafTemplate(map).getBytes(StandardCharsets.UTF_16);
        return generatePdfFromHtml(new String(data, StandardCharsets.UTF_16));
    }

    @SneakyThrows
    private ByteArrayOutputStream generatePdfFromHtml(String data) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ITextRenderer iTextRenderer = new ITextRenderer();

        iTextRenderer.setDocumentFromString(data);
        iTextRenderer.layout();
        iTextRenderer.createPDF(byteArrayOutputStream);

        return byteArrayOutputStream;
    }

    private String parseThymeleafTemplate(Map<String, Object> map) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF_16");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        map.keySet()
                .forEach(
                        k -> context.setVariable(k, map.get(k))
                );
        return templateEngine.process("mafia_template", context);
    }
}
