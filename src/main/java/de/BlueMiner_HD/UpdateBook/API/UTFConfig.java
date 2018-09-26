package de.BlueMiner_HD.UpdateBook.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

public class UTFConfig extends YamlConfiguration {

    public UTFConfig(File file) {
        try {
            load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(File file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        Files.createParentDirs(file);
        String data = this.saveToString();
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

        try {
            writer.write(data);
        } finally {
            writer.close();
        }
    }

    @Override
    public String saveToString() {
        try {

            Field fy = ReflectUtil.getField(getClass(), "yamlOptions");
            Field fr = ReflectUtil.getField(getClass(), "yamlRepresenter");
            Field fYaml = ReflectUtil.getField(getClass(), "yaml");

            ReflectUtil.setAccessible(true, fy, fr, fYaml);

            DumperOptions yamlOptions = (DumperOptions) fy.get(this);
            Representer yamlRepresenter = (Representer) fr.get(this);
            Yaml yaml = (Yaml) fYaml.get(this);
            DumperOptions.FlowStyle fs = DumperOptions.FlowStyle.BLOCK;

            yamlOptions.setIndent(this.options().indent());
            yamlOptions.setDefaultFlowStyle(fs);
            yamlOptions.setAllowUnicode(true);
            yamlRepresenter.setDefaultFlowStyle(fs);

            String header = this.buildHeader();
            String dump = yaml.dump(this.getValues(false));

            if (dump.equals("{}\n"))
                dump = "";

            return header + dump;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error while running this#saveToString()";
    }

    @Override
    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        this.load(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
    }
}
