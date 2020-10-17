package com.byandev.subjetpackpro1.utils

import com.byandev.subjetpackpro1.data.MovieEntity
import com.byandev.subjetpackpro1.data.TvShowEntity

object DataDummy {

    fun generateDataMovie() : List<MovieEntity> {
        val movieEntity = ArrayList<MovieEntity>()

        movieEntity.add(MovieEntity(
            "m1",
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "Action, Adventure, Fantasy",
            "12/21/2018",
            "https://image.tmdb.org/t/p/w1280/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m2",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "Drama, Music",
            "11/02/2018",
            "https://image.tmdb.org/t/p/w1280/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m3",
            "Alita: Battle Angel",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "Action, Science Fiction, Adventure",
            "02/14/2019",
            "https://image.tmdb.org/t/p/w1280/xRWht48C2V8XNfzvPehyClOvDni.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m4",
            "Creed II",
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "Drama",
            "11/21/2018",
            "https://image.tmdb.org/t/p/w1280/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m5",
            "Mulan",
            "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
            "Action, Adventure, Drama, Fantasy",
            "09/04/2020 ",
            "https://image.tmdb.org/t/p/w1280/ffVoswXHDZvyi53yJ1Q4rb9I60q.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m6",
            "Peninsula",
            "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
            "Action, Horror, Thriller",
            "07/15/2020",
            "https://image.tmdb.org/t/p/w1280/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m7",
            "One Night in Bangkok",
            "A hit man named Kai flies into Bangkok, gets a gun, and orders a cab. He offers a professional female driver big money to be his all-night driver. But when she realizes Kai is committing brutal murders at each stop, it's too late to walk away. Meanwhile, an offbeat police detective races to decode the string of slayings before more blood is spilled.",
            "Action, Thriller",
            "08/25/2020",
            "https://image.tmdb.org/t/p/w1280/i4kPwXPlM1iy8Jf3S1uuLuwqQAV.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m8",
            "We Bare Bears: The Movie",
            "When Grizz, Panda, and Ice Bear's love of food trucks and viral videos went out of hand, it catches the attention of Agent Trout from the National Wildlife Control, who pledges to restore the “natural order” by separating them forever. Chased away from their home, the Bears embark on an epic road trip as they seek refuge in Canada, with their journey being filled with new friends, perilous obstacles, and huge parties. The risky journey also forces the Bears to face how they first met and became brothers, in order to keep their family bond from splitting apart.",
            "Family, Animation, Adventure, Comedy, Mystery",
            "06/30/2020",
            "https://image.tmdb.org/t/p/w1280/kPzcvxBwt7kEISB9O4jJEuBn72t.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m9",
            "Joker",
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
            "Crime, Thriller, Drama",
            "10/02/2019",
            "https://image.tmdb.org/t/p/w1280/bfHlGMDq2RqRT1xmjq9NJSzfhCv.jpg"
        ))
        movieEntity.add(MovieEntity(
            "m10",
            "Scoob!",
            "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
            "Family, Animation, Comedy, Adventure",
            "07/08/2020",
            "https://image.tmdb.org/t/p/w1280/jHo2M1OiH9Re33jYtUQdfzPeUkx.jpg"
        ))

        return movieEntity
    }

    fun generateDataTvShow() : List<TvShowEntity> {
        val tvShowEntity = ArrayList<TvShowEntity>()

        tvShowEntity.add(TvShowEntity(
            "tv1",
            "Fear the Walking Dead",
            "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
            "Action & Adventure, Drama",
            "2015",
            "https://image.tmdb.org/t/p/w1280/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv2",
            "The Walking Dead",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "2010",
            "https://image.tmdb.org/t/p/w1280/qgjP2OrrX9gc6M270xdPnEmE9tC.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv3",
            "Emily in Paris",
            "When ambitious Chicago marketing exec Emily unexpectedly lands her dream job in Paris, she embraces a new life as she juggles work, friends and romance.",
            "Drama, Comedy",
            "2020",
            "https://image.tmdb.org/t/p/w1280/kwMqIYOC4U9eK4NZnmmyD8pDEOi.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv4",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "Drama, Mystery",
            "2017",
            "https://image.tmdb.org/t/p/w1280/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv5",
            "Lucifer",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "Crime, Sci-Fi & Fantasy",
            "2016",
            "https://image.tmdb.org/t/p/w1280/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv6",
            "The Boys",
            "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
            "Sci-Fi & Fantasy, Action & Adventure",
            "2019",
            "https://image.tmdb.org/t/p/w1280/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv7",
            "The Walking Dead: World Beyond",
            "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
            "Drama, Sci-Fi & Fantasy, Mystery",
            "2020",
            "https://image.tmdb.org/t/p/w1280/z31GxpVgDsFAF4paZR8PRsiP16D.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv8",
            "The 100",
            "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
            "Sci-Fi & Fantasy, Drama, Action & Adventure",
            "2014",
            "https://image.tmdb.org/t/p/w1280/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv9",
            "The Vampire Diaries",
            "The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.",
            "Drama, Fantasy, Horror, Romance",
            "2009",
            "https://image.tmdb.org/t/p/w1280/b3vl6wV1W8PBezFfntKTrhrehCY.jpg"
        ))
        tvShowEntity.add(TvShowEntity(
            "tv10",
            "The Umbrella Academy",
            "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "Action & Adventure, Sci-Fi & Fantasy, Comedy, Drama",
            "2019",
            "https://image.tmdb.org/t/p/w1280/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg"
        ))

        return tvShowEntity
    }
}