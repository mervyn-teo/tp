package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMedicineUsageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new DeleteMedicineUsageCommandParser object
 */
public class DeleteMedicineUsageCommandParser implements Parser<DeleteMedicineUsageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMedicineUsageCommand
     * and returns a DeleteMedicineUsageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteMedicineUsageCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteMedicineUsageCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteMedicineUsageCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC);

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());

        return new DeleteMedicineUsageCommand(nric, index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
