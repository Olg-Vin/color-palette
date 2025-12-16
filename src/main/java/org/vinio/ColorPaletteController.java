package org.vinio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ColorPaletteController {

    /**
     * Тема приложения:
     * "Цветовая палитра из переменных окружения"
     * Jenkins передаёт 3 цвета, приложение отображает их в HTML
     */
    @GetMapping(value = "/", produces = "text/html; charset=UTF-8")
    public String palette() {
        String color1 = getEnv("COLOR_PRIMARY");
        String color2 = getEnv("COLOR_SECONDARY");
        String color3 = getEnv("COLOR_ACCENT");

        return """
                <!DOCTYPE html>
                <html lang=\"ru\">
                <head>
                    <meta charset=\"UTF-8\">
                    <title>Color Palette</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f5f5f5;
                            padding: 40px;
                        }
                        h1 {
                            margin-bottom: 20px;
                        }
                        .palette {
                            display: flex;
                            gap: 20px;
                        }
                        .color-card {
                            width: 150px;
                            height: 150px;
                            border-radius: 12px;
                            color: white;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-weight: bold;
                            box-shadow: 0 4px 10px rgba(0,0,0,0.15);
                        }
                    </style>
                </head>
                <body>
                    <h1>Цветовая палитра</h1>
                    <div class=\"palette\">
                        <div class=\"color-card\" style=\"background-color:%s\">%s</div>
                        <div class=\"color-card\" style=\"background-color:%s\">%s</div>
                        <div class=\"color-card\" style=\"background-color:%s\">%s</div>
                    </div>
                </body>
                </html>
                """.formatted(color1, color1, color2, color2, color3, color3);
    }

    private String getEnv(String name) {
        String value = System.getenv(name);
        return value != null ? value : "#999999";
    }
}
