package br.com.edson.spring.boot.hfinitializr.util;

import java.util.Collections;
import java.util.List;

import br.com.edson.spring.boot.hfinitializr.domain.Bug;
import br.com.edson.spring.boot.hfinitializr.domain.Note;
/**
* Class      : ListUtils
* Description: Helper class providing functionalities to provide safe
*              iteraction when using List<>.
*
* @author Edson Martins
*/
public class ListUtils {
	
	private ListUtils() {
	    throw new IllegalStateException("List Utility class");
	  }

	public static List<Note> safeNoteList(List<Note> other) {
		return other == null ? Collections.emptyList() : other;
	}
	
	public static List<Bug> safeBugList(List<Bug> other) {
		return other == null ? Collections.emptyList() : other;
	}
	
}
