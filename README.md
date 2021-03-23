# Hearthstone-Game-Project
4th Semester Game Project: Hearthstone Replica.
This is a turn-based card game between two opponents. 
Players use their limited mana crystals to play abilities or summon minions to attack the opponent, with the goal of destroying the opponent's hero. 
This involved implementation using Java OOP (Object-Oriented Programming) and GUI swing features.

-You can run the game through the 'Controller.java' file.
![hearthstoneGame](https://user-images.githubusercontent.com/51987270/112107603-d5f7e080-8bb7-11eb-957d-0536b36f2f7e.JPG)

**General Game Rules List**
-The player who goes first is determined randomly and draws three starting cards.
-The player who goes second gets an extra starting card.
-Each player draws a card every turn.
-Each player is granted one additional Mana Crystal, every turn.
-Each players Mana Crystals are replenished with Mana every turn.
-The maximum Mana Pool a player can have is ten.
-The maximum amount of cards a player can have is ten.
-You can have a maximum of seven minions on the board at once.

**Game End**
The game ends when one of the two heroes is defeated by making its health points reach 0. The
remaining hero is thus the winner of the game.

**Card Types**
There are two types of cards, Minion cards and Spell cards. Any card in the game has certain
attributes/features. These features are:
● Card name
● Mana cost: The number of mana crystals the player must have to be able to play this card.
● Rarity: An indication of how precious this card is. The more precious the card is, the more
powerful it is. There are 5 different rarities in the game ordered from the least precious to the
most precious: basic, common, rare, epic, and legendary . Any deck can include up to two copies
of any card except for legendary cards which the player can only have one copy of.

**Minions**
Minions are a type of cards that represent creatures that the hero summons either to attack the
opponent’s minions or the opponent hero.
Besides the common attributes of all cards, minions have the following extra attributes:
● Attack : a number representing how much damage will this minion inflict to the attacked target
(either another minion or a hero) or to the attacker.
● Health : a number represents how much damage the minion can receive before he dies and gets
removed from the field. The minion will keep losing health when he attacks/ gets attacked until
it reaches or falls below zero. If so, the minion dies and gets removed from the game.
By default, the player cannot attack with any minion the same turn it is played. He has to wait for at
least one extra turn to be able to attack with it.

Some types of minions can have some extra feature/s that affect other game elements
● Charge : minions with charge do not need to wait a turn to be able to attack. They can attack in
the same turn they are played.
● Divine Shield : minions with a divine shield are not affected by the first hit/damage they receive,
regardless of the attacker. After the first attack they receive, the shield will break and they will
normally receive damage.
● Taunt : minions with taunt protect their other friendly minions (minions belonging to the same
hero) and the hero that owns them. This means that if a hero has a taunt minion(s), he and his
non-taunt minions cannot be attacked by the opponent, as long as he has taunt minion(s) on his
field. The opponent has to kill all taunt minions first before he can attack him or his non-taunt
minions.

**Spells**
Spells are magical abilities that the hero can use to affect minions or heroes. Unlike minions, spells are
hero-specific. There are no spells that can be used by all heroes.
There are five different types of spells depending on what the spell can affect. These types are:
1. Minion Target spells that affect minions.
2. Hero Target spells that affect heroes.
3. Field spells that affect the player’s field.
4. Area of Effect spells that can affect both player’s fields.
5. Leeching spells that damage minions while healing its user.

**Heroes and classes**
Each hero has a specific proficiency ( class ), distinguishing him/her from other heroes. According to the
hero’s class, each hero has a special ability ( hero power ) that he/she can use once per turn. Using the
hero power will always cost 2 mana crystals.
There are 4 herous:
1- Mage ( represented by Jaina Proudmoore)
2- Hunter (Rexxar)
3- Paladin (Uther Lightbringer)
4- Priest (Anduin Wrynn)
5- Warlock (Gul'dan)
