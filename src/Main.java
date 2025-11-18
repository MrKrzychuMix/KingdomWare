import Event.*;
import Kingdom.Building.BuildingType;
import Kingdom.Kingdom;
import Kingdom.Military.Unit.UnitType;
import Resource.ResourceType;
import Settings.ConsoleLogger;
import Settings.EventBus;
import Settings.GameEngine;
import Settings.RecruitmentCostCalculator;


public class Main {
    public static void main(String[] args) {
        // --- FAZA 1: INICJALIZACJA SYSTEMU ---
        System.out.println("=== INICJALIZACJA GRY ===");

        // Stwórz kluczowe komponenty backendu
        EventBus eventBus = new EventBus();
        Kingdom kingdom = new Kingdom("Królestwo Testowe");
        RecruitmentCostCalculator recruitmentCostCalculator = new RecruitmentCostCalculator();
        GameEngine gameEngine = new GameEngine(kingdom, eventBus,recruitmentCostCalculator);


        // Stwórz i podłącz subskrybentów (logikę i widok)
        ConsoleLogger logger = new ConsoleLogger();
        RecruitmentCostCalculator costCalculator = new RecruitmentCostCalculator();

        // Loger nasłuchuje na wszystko, co ma komunikat
        eventBus.subscribe(TurnStartedEvent.class, logger);
        eventBus.subscribe(RecruitmentResult.class, logger);
        eventBus.subscribe(RandomResourceEvent.class, logger);
        eventBus.subscribe(CostModifier.class, logger);
        eventBus.subscribe(UnitCostChangeEvent.class, logger);

        // Kalkulator kosztów nasłuchuje na zdarzenia modyfikujące cenę
        eventBus.subscribe(TurnStartedEvent.class, costCalculator);
        eventBus.subscribe(CostModifier.class, costCalculator);
        eventBus.subscribe(RandomResourceEvent.class, costCalculator);
        eventBus.subscribe(ResourceLostEvent.class, costCalculator);
        eventBus.subscribe(UnitCostChangeEvent.class, costCalculator);

        System.out.println("=== KONIEC INICJALIZACJI ===");
        System.out.println("\n=== ROZPOCZĘCIE SYMULACJI ===");
        gameEngine.nextTurn();
        // Tura 1: Gracz buduje farmę i koszary, a potem kończy turę
        gameEngine.handleBuildRequest(BuildingType.FARM, kingdom);
        gameEngine.handleBuildRequest(BuildingType.BARRACK, kingdom);
        gameEngine.handleBuildRequest(BuildingType.BARRACK, kingdom);
        gameEngine.nextTurn(); // Produkcja, postęp budowy...

        // Tura 2: Gracz nic nie robi, tylko kończy turę
        gameEngine.nextTurn();

        // Tura 3: Gracz rekrutuje 3 mieczników
        gameEngine.recruitUnit(UnitType.SWORDSMAN);
        gameEngine.recruitUnit(UnitType.SWORDSMAN);
        gameEngine.recruitUnit(UnitType.SWORDSMAN);
        gameEngine.nextTurn(); // Koszt utrzymania dla 3 mieczników zostanie naliczony

        // Tura 4: Gracz próbuje zrekrutować Rycerza bez stajni (oczekujemy błędu) NIE OTRZYMALISMY BLEDU - CZEMU?
        gameEngine.recruitUnit(UnitType.KNIGHT);
        gameEngine.nextTurn();

        // Tura 5: Gracz próbuje zrekrutować Łucznika bez strzelnicy (oczekujemy wyższego kosztu)
        gameEngine.recruitUnit(UnitType.ARCHER);
        gameEngine.nextTurn();

        System.out.println("\n=== KONIEC SYMULACJI ===");
        // Możesz tu wyświetlić ostateczny stan królestwa, aby sprawdzić, czy wszystko się zgadza.
        System.out.println("Ostateczny stan armii: " + kingdom.getArmy().getSize() + " jednostek.");
        System.out.println("Ostateczny stan złota: " + kingdom.getTreasury().getResourceAmount(ResourceType.GOLD));
        System.out.println("Ostateczny stan drewna: " + kingdom.getTreasury().getResourceAmount(ResourceType.WOOD));
        System.out.println("Ostateczny stan jedzenia: " + kingdom.getTreasury().getResourceAmount(ResourceType.FOOD));
        System.out.println("Ostateczny stan sily: " + kingdom.getTreasury().getResourceAmount(ResourceType.MANPOWER));
    }
}