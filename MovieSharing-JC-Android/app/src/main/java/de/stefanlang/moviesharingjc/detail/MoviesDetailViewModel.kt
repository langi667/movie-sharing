package de.stefanlang.moviesharingjc.detail

import androidx.lifecycle.ViewModel
import java.util.*

class MoviesDetailViewModel: ViewModel() {
    val title: String
        get() {
            return "Face/Off Face Face/Off Face Face/Off Face Face/Off Face".uppercase(
                Locale.ROOT
            )
        }

    val description: String
        get() {
            return "When the 1997 film 'Face/Off' starring John Travolta and Nicolas Cage was released it was a modern action hit. Trailer realized that this film actually has a lot in common with the classic Westerns of the past.\n\nCREDITS: Edited by Ian Beckman and Dustin McLean\nNarrated by Bob Bavnani\nExecutive Producer: Dustin McLean\n\nMore 'Trailer Mix'\n\n Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.   \n" +
                    "\n" +
                    "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet"

        }
}