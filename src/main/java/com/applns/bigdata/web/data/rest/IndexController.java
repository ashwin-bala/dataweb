package com.applns.bigdata.web.data.rest;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.applns.bigdata.web.data.events.EventsSender;
import com.applns.bigdata.web.data.models.EventMessage;
import com.applns.bigdata.web.data.models.web.ArtWorkTypeTitlesObj;
import com.applns.bigdata.web.data.models.web.ArtWorkTypeTitlesResponse;
import com.applns.bigdata.web.data.models.web.DataWebRequest;
import com.applns.bigdata.web.data.models.web.EarliestArtDatesPlaceOfOriginObj;
import com.applns.bigdata.web.data.models.web.EarliestArtDatesPlaceOfOriginResponse;
import com.applns.bigdata.web.data.models.web.PopularStyleTitlesObj;
import com.applns.bigdata.web.data.models.web.PopularStyleTitlesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@Controller
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private EventsSender eventsSender;

	@Autowired
	private CompositeMeterRegistry meterRegistry;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("datarequest", new DataWebRequest());
		return "greeting";
	}

	@PostMapping("/getdata")
	public String greetingSubmit(@ModelAttribute DataWebRequest dataWebRequest, Model model) {
		model.addAttribute("datarequest", dataWebRequest);

		Counter eventsCounter = meterRegistry.counter("app.events");
		EventMessage eventMessage = new EventMessage();
		eventMessage.setFromDate("2023-10-11");
		try {
			eventsSender.sendMessage(eventMessage);
			eventsCounter.increment();
			log.info("Success");
		} catch (JsonProcessingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return "result";
	}

	@PostMapping("/earliestArtDatesByPlaceOfOrigin")
	public String earliestArtDatesByPlaceOfOrigin(@ModelAttribute DataWebRequest dataWebRequest, Model model) throws Exception {
		model.addAttribute("datarequest", dataWebRequest);
		String EP = Optional.ofNullable(System.getenv("DATA_ANALYSER_ENDPOINTS"))
				.orElseThrow(() -> new Exception("DATA_ANALYSER_ENDPOINTS is not set in the environment"));
		String url = EP+"/earliestArtDatesByPlaceOfOrigin";
		EarliestArtDatesPlaceOfOriginResponse earliestArtDatesPlaceOfOriginResponse = restTemplate.getForObject(url, EarliestArtDatesPlaceOfOriginResponse.class);

		log.info("Response:", earliestArtDatesPlaceOfOriginResponse.getResponse());
		
		class EarliestArtDatesPlaceOfOriginObjComparator implements Comparator<EarliestArtDatesPlaceOfOriginObj> {
		    public int compare(EarliestArtDatesPlaceOfOriginObj s1, EarliestArtDatesPlaceOfOriginObj s2) {
		        return Integer.compare(Integer.parseInt(s1.getEarliestStartDate()), Integer.parseInt(s2.getEarliestStartDate()));
		    }
		}
		// fill array
		Collections.sort(earliestArtDatesPlaceOfOriginResponse.getResponse(),new EarliestArtDatesPlaceOfOriginObjComparator());
		
		
	    model.addAttribute("dataAnalysisResults", earliestArtDatesPlaceOfOriginResponse.getResponse());

		return "earliestArtDatesByPlaceOfOrigin";
	}
	
	@PostMapping("/mostPopularStyleTitles")
	public String mostPopularStyleTitles(@ModelAttribute DataWebRequest dataWebRequest, Model model) throws Exception {
		model.addAttribute("datarequest", dataWebRequest);
		String EP = Optional.ofNullable(System.getenv("DATA_ANALYSER_ENDPOINTS"))
				.orElseThrow(() -> new Exception("DATA_ANALYSER_ENDPOINTS is not set in the environment"));
		String url = EP+"/mostPopularStyleTitles";
		PopularStyleTitlesResponse popularStyleTitlesResponse = restTemplate.getForObject(url, PopularStyleTitlesResponse.class);

		log.info("Response:", popularStyleTitlesResponse.getResponse());
		
		class PopularStyleTitlesObjComparator implements Comparator<PopularStyleTitlesObj> {
		    public int compare(PopularStyleTitlesObj s1, PopularStyleTitlesObj s2) {
		        return Integer.compare(Integer.parseInt(s2.getPopularity()), Integer.parseInt(s1.getPopularity()));
		    }
		}
		// fill array
		Collections.sort(popularStyleTitlesResponse.getResponse(),new PopularStyleTitlesObjComparator());
		
	    model.addAttribute("dataAnalysisResults", popularStyleTitlesResponse.getResponse());

		return "popularStyleTitlesResponse";
	}
	
	@PostMapping("/getAllArtWorkTypeTitles")
	public String getAllArtWorkTypeTitles(@ModelAttribute DataWebRequest dataWebRequest, Model model) throws Exception {
		model.addAttribute("datarequest", dataWebRequest);
		String EP = Optional.ofNullable(System.getenv("DATA_ANALYSER_ENDPOINTS"))
				.orElseThrow(() -> new Exception("DATA_ANALYSER_ENDPOINTS is not set in the environment"));
		String url = EP+"/getAllArtWorkTypeTitles";
		ArtWorkTypeTitlesResponse artWorkTypeTitlesResponse = restTemplate.getForObject(url, ArtWorkTypeTitlesResponse.class);

		log.info("Response:", artWorkTypeTitlesResponse.getResponse());
		
		class ArtWorkTypeTitlesObjComparator implements Comparator<ArtWorkTypeTitlesObj> {
		    public int compare(ArtWorkTypeTitlesObj s1, ArtWorkTypeTitlesObj s2) {
		        return Integer.compare(Integer.parseInt(s2.getNumRows()), Integer.parseInt(s1.getNumRows()));
		    }
		}
		// fill array
		Collections.sort(artWorkTypeTitlesResponse.getResponse(),new ArtWorkTypeTitlesObjComparator());
				
	    model.addAttribute("dataAnalysisResults", artWorkTypeTitlesResponse.getResponse());

		return "artWorkTypeTitlesResponse";
	}
}