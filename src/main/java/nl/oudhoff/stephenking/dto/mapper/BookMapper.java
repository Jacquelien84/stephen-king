package nl.oudhoff.stephenking.dto.mapper;

import nl.oudhoff.stephenking.dto.input.BookInputDto;
import nl.oudhoff.stephenking.dto.output.BookOutputDto;
import nl.oudhoff.stephenking.model.Book;

public class BookMapper {

    public static Book fromInputDtoToModel (BookInputDto inputDto){
        Book model = new Book();
        model.setId(inputDto.id);
        model.setTitle(inputDto.title);
        model.setAuthor(inputDto.author);
        model.setOriginalTitle(inputDto.originalTitle);
        model.setReleased(inputDto.released);
        model.setMovieAdaptation(inputDto.movieAdaptation);
        model.setDescription(inputDto.description);
        return model;
    }

    public static BookOutputDto fromModelToOutputDto (Book model) {
        BookOutputDto outputDto = new BookOutputDto();
        outputDto.setId(model.getId());
        outputDto.setTitle(model.getTitle());
        outputDto.setAuthor(model.getAuthor());
        outputDto.setOriginalTitle(model.getOriginalTitle());
        outputDto.setReleased(model.getReleased());
        outputDto.setMovieAdaptation(model.getMovieAdaptation());
        outputDto.setDescription(model.getDescription());
        return outputDto;
    }
}
