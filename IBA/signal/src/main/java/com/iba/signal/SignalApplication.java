package com.iba.signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.iba.signal.modal.Keywords;
import com.iba.signal.modal.Signals;
import com.iba.signal.repository.KeywordRepository;
import com.iba.signal.repository.SignalRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class SignalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignalApplication.class, args);
    }
}

@Component
class DataLoader implements ApplicationRunner {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private SignalRepository signalRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadKeywords();
        loadSignals();
    }

    private void loadKeywords() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("keywords.csv").getInputStream()));
			// Skip the header row
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				Keywords keyword = new Keywords();
				keyword.setId(Long.parseLong(data[0]));
				keyword.setName(data[1]);
				keyword.setDescription(data[2]);
				keywordRepository.save(keyword);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadSignals() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("signals.csv").getInputStream()));
			// Skip the header row
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length >= 5 && !data[1].isEmpty() && !data[2].isEmpty() && !data[4].isEmpty()) {
					Signals signal = new Signals();
					signal.setNodeId(data[0]);
					signal.setSamplingIntervalMs(Integer.parseInt(data[1]));
					signal.setDeadbandValue(Integer.parseInt(data[2]));
					signal.setDeadbandType(data[3]);
					signal.setActive(Integer.parseInt(data[4]));
					signal.setKeywords(data[5]);
					// set other fields
					signalRepository.save(signal);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
