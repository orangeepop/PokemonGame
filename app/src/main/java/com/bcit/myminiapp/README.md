### Pokemon App

An app that lets players create an account, catch pokemons in the pokeworld, and battle pokemons.

When the player creates an account, the password is hashed in Room using BCrypt. Since Room is a local database, hashing passwords is not technically needed.

In the pokeworld, the player encounters a random pokemon (a randomly generated id number is to fetch the random pokemon from the PokeAPI), and can try to catch it. Catch success is 50%, determined by randomly generating a boolean. If the pokemon is successfully caught, it is added to the `caught_pokemons_table`, which references the `player_table` by the player's `uid` as a foreign key to keep track of different players' pokemon rosters.

If the catch is unsuccessful, the player may choose a pokemon from their roster to battle the wild pokemon. In the battle, the player's pokemon will attack first, followed by the wild pokemon, and this will repeat until one pokemon's current hp drops to 0. `currentHP` is used instead of `HP` in the battle so that the pokemons in the players' roster always start with full HP in a battle. The battle UI listens for changes in the battle state and updates accordingly via `LaunchedEffect`. If the wild pokemon is victorious, it escapes. If the player's pokemon wins, the player will catch the wild pokemon.

In the player's home page, the player may view their pokemon roster.
