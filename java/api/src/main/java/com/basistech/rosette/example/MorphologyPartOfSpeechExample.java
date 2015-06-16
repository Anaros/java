package com.basistech.rosette.example;

import com.basistech.rosette.api.RosetteAPI;
import com.basistech.rosette.api.RosetteAPIException;
import com.basistech.rosette.apimodel.MorphologyResponse;
import com.basistech.rosette.apimodel.PartOfSpeech;

import java.io.IOException;
import java.net.URISyntaxException;

public class MorphologyPartOfSpeechExample extends AbstractExample {
    /**
     * Main program.
     * Creates a RosetteAPI instance with the API key defined in rosette.api.key property.
     * Gets complete morphology analysis as a demonstration of usage.
     *
     * @param args not used
     * @throws java.net.URISyntaxException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        String key = "";
        if (args.length == 2) {
            if (args[0].equals("--key")) {
                key = args[1];
            }
        } else {
            usage();
        }
        init(key, null);
        doMorphology(text);
    }

    /**
     * Sends string Morphology request.
     * @param text
     */
    private static void doMorphology(String text) {
        try {
            MorphologyResponse response = rosetteAPI.getMorphology(RosetteAPI.MorphologicalFeature.PARTS_OF_SPEECH, text, null, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints MorphologyResponse.
     * @param response
     */
    private static void print(MorphologyResponse response) {
        System.out.println(response.getRequestId());
        String result = "\n";
        if (response.getPosTags() != null) {
            for (PartOfSpeech partOfSpeech : response.getPosTags()) {
                result += partOfSpeech.getText() + "\t" + partOfSpeech.getPos() + "\n";
            }
        }
        result += "\n";
        System.out.println(result);
    }
}
