package es.ulpgc.dis.kata2.model;

public record Title(String id, Title.TitleType titleType,
					String primaryTitle, String originalTitle) {

	public enum TitleType {
		VideoGame,
		TvPilot,
		Movie,
		TvSeries,
		TvMiniSeries,
		Short,
		TvSpecial,
		TvShort,
		Video,
		TvMovie,
		TvEpisode
	}
}
