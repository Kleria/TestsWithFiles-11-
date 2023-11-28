import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideFileTest {

    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded =
                $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']")
                        .download();
        try(InputStream is = new FileInputStream(downloaded)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions  "));
        };
        String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
        System.out.println();
    }
    @Test
    void uploadFileTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("cat.png");
        $(".qq-file-name").shouldHave(text("cat.png"));
    }
}


