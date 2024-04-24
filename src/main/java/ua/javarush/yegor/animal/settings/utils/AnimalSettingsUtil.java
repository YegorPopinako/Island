package ua.javarush.yegor.animal.settings.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.animal.settings.Settings;

import java.io.InputStream;

@NoArgsConstructor
public class AnimalSettingsUtil {

    public static AnimalUnit getAnimalUnit(Class<? extends Animal> clazz) {
        if (clazz.isAnnotationPresent(Settings.class)) {
            String settingsFile = clazz.getAnnotation(Settings.class).settingsFile();

            try (InputStream inputStream = AnimalUnit.class.getClassLoader().getResourceAsStream(settingsFile)) {
                if (inputStream != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(inputStream, AnimalUnit.class);
                }
            } catch (Exception e) {
                e.getCause();
            }
        }
        throw new IllegalArgumentException("Class is not annotated with @Settings: " + clazz.getName());
    }
}
