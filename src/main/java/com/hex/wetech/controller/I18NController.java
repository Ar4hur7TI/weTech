package com.hex.wetech.controller;

import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.I18nTO;
import com.hex.wetech.utils.CacheMapUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/i18n")
public class I18NController {

    private static final String CACHE_KEY = "I18N";
    private static final String I18N_KEY = "now";
    private static final String TRANSCRIPT_EN_KEY = "eng";
    private static final String TRANSCRIPT_FR_KEY = "french";
    private static final String[] EN_TRANSCRIPT = {"welcome to the meeting", "We are honored that you can attend this conference", "This is just a test, so the meeting is ending"};
    private static final String[] FR_TRANSCRIPT = {"bienvenue à la réunion", "Nous sommes honorés que vous puissiez assister à cette conférence", "Ce n'est qu'un test, donc la réunion se termine"};

    @PostMapping("/get")
    public R getI18N() {
        String result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        if (result == null) {
            CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(I18N_KEY, "eng");
            result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        }
        return R.ok(result);
    }

    @GetMapping("/loadLang")
    @ResponseBody
    public String loadLang() {
        String result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        if ("french".equalsIgnoreCase(result))
            return "{\"caption-header\":\"Sous-titrage en direct\", \"get-live-caption\":\"Obtenir des sous-titres en direct\", \"get-live-caption-stop\":\"Arrêter le streaming\", \"english-language\":\"English\", \"french-language\":\"Français\", \"live-caption-empty\":\"La transcription s'affichera ici\", \"hotmail\":\"PS je t'aime. Obtenez votre transcription gratuite de l'événement en direct sur\"}";
        else
            return "{\"caption-header\":\"Event Live Captioning\", \"get-live-caption\":\"Get Live Captions\", \"get-live-caption-stop\":\"Stop Streaming\", \"english-language\":\"English\", \"french-language\":\"Français\", \"live-caption-empty\":\"Transcription will display here\", \"hotmail\":\"PS: I love you. Get your free live-event transcription at\"}";
    }

    @PostMapping("/getTranscript")
    public R getTranscript() {
        String result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        int cnt;
        String[] transcript;
        if ("french".equalsIgnoreCase(result)) {
            cnt = (int) CacheMapUtil.getCacheMapByKey(CACHE_KEY).getOrDefault(TRANSCRIPT_FR_KEY, 0);
            transcript = FR_TRANSCRIPT;
        } else {
            cnt = (int) CacheMapUtil.getCacheMapByKey(CACHE_KEY).getOrDefault(TRANSCRIPT_EN_KEY, 0);
            transcript = EN_TRANSCRIPT;
        }
        if (cnt >= transcript.length) {
            cnt = transcript.length;
        } else {
            cnt++;
        }
        CacheMapUtil.getCacheMapByKey(CACHE_KEY).put("french".equalsIgnoreCase(result) ? TRANSCRIPT_FR_KEY : TRANSCRIPT_EN_KEY, cnt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            sb.append(transcript[i]).append("<br/>");
        }
        return R.ok(sb.toString());
    }

    @PostMapping("/switch")
    public R switch18N(@RequestBody @Validated I18nTO to) {
        CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(I18N_KEY, to.getLanguage());
        return R.ok(to.getLanguage());
    }
}
