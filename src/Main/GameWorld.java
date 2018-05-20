package Main;

import Commands.*;
import Modifiers.BaseModifier;
import Modifiers.IndustrialModifier;
import Modifiers.MedievalModifier;
import Nations.Sweden;
import Nations.Ukraine;
import Units.Unit;
import Units.UnitInterface;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameWorld {
    List<Player> players = new ArrayList<>();
    List<UnitInterface> autoattackers = new ArrayList<>();
    int step = 0;

    public GameWorld(int n){
        for(int i = 0; i < n; i++) {
            System.out.printf("Player %d choose your nation:\n", i + 1);
            System.out.println("1 - Ukraine");
            System.out.println("2 - Sweden");
            Scanner in = new Scanner(System.in);
            int nationCode = -1;
            try {
                nationCode = in.nextInt();
            } catch (InputMismatchException ignored){}
            if(nationCode == 1){
                players.add(new Player(new Ukraine()));
                System.out.println("Ukraine it is!");
            } else if(nationCode == 2){
                players.add(new Player(new Sweden()));
                System.out.println("Sweden it is!");
            } else {
                System.out.println("Incorrect nation. Try again!");
                i--;
            }
        }
        game();
    }

    int game(){
        while (true){
            step++;
            if(step == 10){
                System.out.println("Добро пожаловать в средневековье!");
                for(Player p : players){
                    for(UnitInterface u : p.units){
                        UnitInterface tmp = new MedievalModifier(u);
                        p.units.remove(u);
                        p.units.add(tmp);
                    }
                }
            }
            if(step == 20){
                System.out.println("Добро пожаловать в эпоху просвещения!");
                for(Player p : players){
                    for(UnitInterface u : p.units){
                        UnitInterface tmp = new IndustrialModifier(u);
                        p.units.remove(u);
                        p.units.add(tmp);
                    }
                }
            }
            for(int i = 0; i < players.size(); i++){
                System.out.printf("Игрок %d, ваш ход (%d)\n", i + 1, step);
                printMap(i);
                if(players.get(i).units.size() == 0){
                    System.out.println("У вас нет доступных юнитов! Создайте новый!");
                    createNewUnit(players.get(i));
                } else if(players.get(i).units.size() < 5) {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Создайте новый юнит, или походите новым");
                    System.out.println("1 - создать новый");
                    System.out.println("2 - сделать ход (default)");
                    int type = -1;
                    try {
                        type = in.nextInt();
                    } catch (InputMismatchException ignored){}
                    if(type == 1) {
                        createNewUnit(players.get(i));
                    } else {
                        moveUnit(players.get(i));
                    }
                } else {
                    moveUnit(players.get(i));
                }
                cleanUp();
                if(gameOver()) return 0;
            }
        }
    }

    boolean gameOver(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).units.size() == 0 && step != 1){
                if(i == 0){
                    System.out.println("Игрок 2 выиграл!");
                    return true;
                } else {
                    System.out.println("Игрок 1 выиграл!");
                    return true;
                }
            }
        }
        return false;
    }

    void createNewUnit(Player p){
        Scanner in = new Scanner(System.in);
        System.out.println("Выбирите тип:");
        System.out.println("1 - рыцарь (default)");
        System.out.println("2 - лучник");
        System.out.println("3 - кавалерия");
        int type = -1;
        try {
            type = in.nextInt();
        } catch (InputMismatchException ignored) {}
        if (type == 2) {
            p.createArcher();
        } else if (type == 3) {
            p.createCavalry();
        } else {
            p.createMelee();
        }
        setCoordinates(p);
    }

    void setCoordinates(Player p){
        System.out.println("Введите координаты: <X> <Y>");
        Scanner in = new Scanner(System.in);
        int x = -1;
        int y = -1;
        try {
            x = in.nextInt();
            y = in.nextInt();
        } catch (InputMismatchException ignored) {}
        if (x < 0 || x > 9 || y < 0 || y > 9 || !notCollidingEnemies(p, x, y)){
            System.out.println("Координаты нелегетимны. Попробуйте ещё раз");
            setCoordinates(p);
        } else {
            if(!notCollidingTeammates(p, x, y)){
                if(!getUnitAtPosition(x, y).addUnit(p, p.units.get(p.units.size() - 1))){
                    System.out.println("Не более 5 юнитов в 1 скваде!");
                    setCoordinates(p);
                }
            }
            p.units.get(p.units.size() - 1).setX(x);
            p.units.get(p.units.size() - 1).setY(y);
        }
    }

    boolean notCollidingEnemies(Player currentPlayer, int x, int y){
        for(Player p : players){
            if(p.equals(currentPlayer)) continue;
            for(UnitInterface u : p.units){
                if(u.getX() == x && u.getY() == y){
                    return false;
                }
            }
        }
        return true;
    }

    boolean notCollidingTeammates(Player p, int x, int y){
        for(UnitInterface u : p.units){
            if(u.getX() == x && u.getY() == y){
                return false;
            }
        }
        return true;
    }

    UnitInterface getUnitAtPosition(int x, int y){
        for(Player p : players){
            for(UnitInterface u : p.units){
                if(u.getX() == x && u.getY() == y){
                    return u;
                }
            }
        }
        return null;
    }

    void moveUnit(Player p){
        moveUnit(p, -1);
    }

    void moveUnit(Player p, int previous){
        Scanner in = new Scanner(System.in);
        int id;
        if(previous == -1) {
            System.out.println("Каким юнитом ходим? Default: 0");
            id = 0;
            try {
                id = in.nextInt();
            } catch (InputMismatchException ignored) {
            }
            if (id >= p.units.size() || id < 0) {
                id = 0;
            }
        } else {
            id = previous;
        }
        boolean autoAttackEnabled = autoattackers.indexOf(p.units.get(id)) != -1;
        if(autoAttackEnabled && autoAttack(p, p.units.get(id))){
            return;
        }
        System.out.println("Куда походить?");
        if(p.units.get(id).getX() > 0 && notCollidingTeammates(p,p.units.get(id).getX() - 1, p.units.get(id).getY())){System.out.println("1 - влево");}
        if(p.units.get(id).getY() < 9 && notCollidingTeammates(p,p.units.get(id).getX(), p.units.get(id).getY() + 1)){System.out.println("2 - вверх");}
        if(p.units.get(id).getX() < 9 && notCollidingTeammates(p,p.units.get(id).getX() + 1, p.units.get(id).getY())){System.out.println("3 - вправо");}
        if(p.units.get(id).getY() > 0 && notCollidingTeammates(p,p.units.get(id).getX(), p.units.get(id).getY() - 1)){System.out.println("4 - вниз (default)");}
        System.out.printf("5 - %s автоатаку\n", autoAttackEnabled ? "Выключить" : "Включить");
        int type = -1;
        try {
            type = in.nextInt();
        } catch (InputMismatchException ignored) {}
        if (type == 1) {
            if(p.units.get(id).getX() > 0) {
                if(!notCollidingEnemies(p,p.units.get(id).getX() - 1, p.units.get(id).getY())){
                    p.units.get(id).executeCommand(new AttackCommand(p.units.get(id),
                            getUnitAtPosition(p.units.get(id).getX() - 1, p.units.get(id).getY() )));
                } else if(notCollidingTeammates(p,p.units.get(id).getX() - 1, p.units.get(id).getY())
                        && notCollidingEnemies(p,p.units.get(id).getX() - 1, p.units.get(id).getY())) {
                    p.units.get(id).executeCommand(new MoveLeftCommand(p.units.get(id)));
                } else if(!notCollidingTeammates(p, p.units.get(id).getX() - 1, p.units.get(id).getY())){
                    if(!getUnitAtPosition(p.units.get(id).getX() - 1, p.units.get(id).getY()).addUnit(p, p.units.get(id))){
                    System.out.println("Не более 5 юнитов в 1 скваде!");
                    moveUnit(p);
                }
                } else {
                    System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                    moveUnit(p);
                }
            } else {
                System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                moveUnit(p);
            }
        } else if (type == 2) {
            if(p.units.get(id).getY() < 9) {
                if(!notCollidingEnemies(p, p.units.get(id).getX(), p.units.get(id).getY() + 1)) {
                    p.units.get(id).executeCommand(new AttackCommand(p.units.get(id),
                            getUnitAtPosition(p.units.get(id).getX(), p.units.get(id).getY() + 1)));
                } else if (notCollidingEnemies(p, p.units.get(id).getX(), p.units.get(id).getY() + 1)
                        && notCollidingTeammates(p, p.units.get(id).getX(), p.units.get(id).getY() + 1)){
                    p.units.get(id).executeCommand(new MoveUpCommand(p.units.get(id)));
                } else if(!notCollidingTeammates(p, p.units.get(id).getX(), p.units.get(id).getY() + 1)){
                    if(!getUnitAtPosition(p.units.get(id).getX(), p.units.get(id).getY() + 1).addUnit(p, p.units.get(id))){
                        System.out.println("Не более 5 юнитов в 1 скваде!");
                        moveUnit(p);
                    }
                } else {
                    System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                    moveUnit(p);
                }
            } else {
                System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                moveUnit(p);
            }
        } else if(type == 3) {
            if (p.units.get(id).getX() < 9) {
                if (!notCollidingEnemies(p,p.units.get(id).getX() + 1, p.units.get(id).getY())) {
                    p.units.get(id).executeCommand(new AttackCommand(p.units.get(id),
                            getUnitAtPosition(p.units.get(id).getX() + 1, p.units.get(id).getY())));
                } else if (notCollidingEnemies(p,p.units.get(id).getX() + 1, p.units.get(id).getY())
                        && notCollidingTeammates(p, p.units.get(id).getX() + 1, p.units.get(id).getY())) {
                    p.units.get(id).executeCommand(new MoveRightCommand(p.units.get(id)));
                } else if(!notCollidingTeammates(p, p.units.get(id).getX() + 1, p.units.get(id).getY())){
                    if(!getUnitAtPosition(p.units.get(id).getX() + 1, p.units.get(id).getY()).addUnit(p, p.units.get(id))){
                        System.out.println("Не более 5 юнитов в 1 скваде!");
                        moveUnit(p);
                    }
                } else {
                    System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                    moveUnit(p);
                }
            } else {
                System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                moveUnit(p);
            }
        } else if (type == 5){
            if(autoAttackEnabled) {
                autoattackers.remove(p.units.get(id));
                moveUnit(p, id);
            }else {
                autoattackers.add(p.units.get(id));
                moveUnit(p, id);
            }
        } else {
            if(p.units.get(id).getY() > 0) {
                if(!notCollidingEnemies(p, p.units.get(id).getX(), p.units.get(id).getY() - 1)) {
                    p.units.get(id).executeCommand(new AttackCommand(p.units.get(id),
                            getUnitAtPosition(p.units.get(id).getX(), p.units.get(id).getY() - 1)));
                } else if (notCollidingEnemies(p, p.units.get(id).getX(), p.units.get(id).getY() - 1)
                        && notCollidingTeammates(p, p.units.get(id).getX(), p.units.get(id).getY() - 1)) {
                    p.units.get(id).executeCommand(new MoveDownCommand(p.units.get(id)));
                } else if(!notCollidingTeammates(p, p.units.get(id).getX(), p.units.get(id).getY() - 1)){
                    if(!getUnitAtPosition(p.units.get(id).getX(), p.units.get(id).getY() - 1).addUnit(p, p.units.get(id))){
                        System.out.println("Не более 5 юнитов в 1 скваде!");
                        moveUnit(p);
                    }
                } else {
                    System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                    moveUnit(p);
                }
            } else {
                System.out.println("Так ходить нельзя. Давай попробуем ещё раз");
                moveUnit(p);
            }
        }
    }

    boolean autoAttack(Player p, UnitInterface unit){
        for(Player enemy : players){
            if(!p.equals(enemy)){
                for(UnitInterface enemyUnit : enemy.units){
                    if((enemyUnit.getX() == unit.getX() + 1 && enemyUnit.getY() == unit.getY()) || (enemyUnit.getY() == unit.getY() + 1 && enemyUnit.getX() == unit.getX())
                            || (enemyUnit.getX() == unit.getX() - 1 && enemyUnit.getY() == unit.getY()) || (enemyUnit.getY() == unit.getY() - 1 && enemyUnit.getX() == unit.getX())){
                        unit.executeCommand(new AttackCommand(unit, enemyUnit));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void cleanUp(){
        for(Player p : players){
            for(UnitInterface u : p.units){
                u.cleanUp();
            }
        }
    }

    void printMap(int i){
        for(int y = 9; y >= 0; y--){
            for(int x = 0; x < 10; x++){
                boolean isPosition = false;
                isPosition = getUnitAtPosition(x,y) != null;
                if(isPosition){
                    boolean isFriendly = false;
                    for(int k = 0; k < players.get(i).units.size(); k++){
                        if (players.get(i).units.get(k).getX() == x && players.get(i).units.get(k).getY() == y){
                            System.out.printf("%d  ", k);
                            isFriendly = true;
                            break;
                        }
                    }
                    if(!isFriendly){
                        System.out.print("X  ");
                    }
                }else {
                    System.out.print(".  ");
                }
            }
            System.out.print('\n');
        }
    }
}
