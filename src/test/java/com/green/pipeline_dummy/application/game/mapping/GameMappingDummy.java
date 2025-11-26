package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.application.game.GameRepository;
import com.green.pipeline_dummy.application.game.search.*;
import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.mapping.*;
import com.green.pipeline_dummy.entity.game.searchtag.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class GameMappingDummy {

    private final GameRepository gameRepository;

    private final VrPlatformRepository vrPlatformRepository;
    private final PlaymodeRepository playmodeRepository;
    private final OsRepository osRepository;
    private final DeviceRepository deviceRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final TagRepository tagRepository;

    private final GameVrRepository gameVrRepository;
    private final GamePlaymodeRepository gamePlaymodeRepository;
    private final GameOsRepository gameOsRepository;
    private final GameDeviceRepository gameDeviceRepository;
    private final GameGenreRepository gameGenreRepository;
    private final GameLanguageRepository gameLanguageRepository;
    private final GameTagRepository gameTagRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void generateMappings() {

        List<Game> games = gameRepository.findAll();

        List<VrPlatform> vrList = vrPlatformRepository.findAll();
        List<Playmode> playmodeList = playmodeRepository.findAll();
        List<Os> osList = osRepository.findAll();
        List<Device> deviceList = deviceRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Language> languageList = languageRepository.findAll();
        List<Tag> tagList = tagRepository.findAll();

        Random random = new Random();

        int batchSize = 100;
        int count = 0;

        for (Game game : games) {

            // VR
            int vrCount = random.nextInt(3);
            for (int i = 0; i < vrCount; i++) {
                VrPlatform vr = vrList.get(random.nextInt(vrList.size()));
                gameVrRepository.save(new GameVr(new GameVrId(game.getGameId(), vr.getId()), game, vr));
            }

            // Playmode
            int pmCount = 1 + random.nextInt(3);
            for (int i = 0; i < pmCount; i++) {
                Playmode pm = playmodeList.get(random.nextInt(playmodeList.size()));
                gamePlaymodeRepository.save(new GamePlaymode(new GamePlaymodeId(game.getGameId(), pm.getId()), game, pm));
            }

            // OS
            int osCount = 1 + random.nextInt(3);
            for (int i = 0; i < osCount; i++) {
                Os os = osList.get(random.nextInt(osList.size()));
                gameOsRepository.save(new GameOs(new GameOsId(game.getGameId(), os.getId()), game, os));
            }

            // Device
            int devCount = 1 + random.nextInt(3);
            for (int i = 0; i < devCount; i++) {
                Device d = deviceList.get(random.nextInt(deviceList.size()));
                gameDeviceRepository.save(new GameDevice(new GameDeviceId(game.getGameId(), d.getId()), game, d));
            }

            // Genre
            int genreCount = 1 + random.nextInt(3);
            for (int i = 0; i < genreCount; i++) {
                Genre g = genreList.get(random.nextInt(genreList.size()));
                gameGenreRepository.save(new GameGenre(new GameGenreId(game.getGameId(), g.getId()), game, g));
            }

            // Language
            int langCount = 1 + random.nextInt(3);
            for (int i = 0; i < langCount; i++) {
                Language l = languageList.get(random.nextInt(languageList.size()));
                gameLanguageRepository.save(new GameLanguage(new GameLanguageId(game.getGameId(), l.getId()), game, l));
            }

            // Tag
            int tagCount = 2 + random.nextInt(4);
            for (int i = 0; i < tagCount; i++) {
                Tag t = tagList.get(random.nextInt(tagList.size()));
                gameTagRepository.save(new GameTag(new GameTagId(game.getGameId(), t.getId()), game, t));
            }

            count++;

            // 🔥 메모리 누수 방지 핵심
            if (count % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
                System.out.println("Flushed batch: " + count);
            }
        }

        // 마지막 남은 데이터 flush
        entityManager.flush();
        entityManager.clear();
    }
}