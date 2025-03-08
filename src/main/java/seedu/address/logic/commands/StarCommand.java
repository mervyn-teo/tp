package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

/**
 * Stars a person in the address book.
 */
public class StarCommand extends Command {

    public static final String COMMAND_WORD = "star";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Stars a person from the address book. \n"
            + "Parameters: Name\n"
            + "Example: " + COMMAND_WORD + "Alice";

    public static final String MESSAGE_SUCCESS = "Person stared: %1$s";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates an StarCommand to add the specified {@code NameContainsKeywordsPredicate}
     */
    public StarCommand(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.getFilteredPersonList();
        Person toStar = model.getFilteredPersonList().get(0);
        toStar.setStar(true);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toStar)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
