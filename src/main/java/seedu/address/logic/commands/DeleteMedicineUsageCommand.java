package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicineusage.MedicineUsage;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Deletes a medicine usage of a person identified by Nric and the index number displayed in the person's details.
 */
public class DeleteMedicineUsageCommand extends Command {

    public static final String COMMAND_WORD = "deletemu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a medicine usage of a person given their NRIC and the index number displayed "
            + "in the person's details.\n"
            + "Parameters: "
            + "ID (must be a positive integer) "
            + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 " + PREFIX_NRIC + " S1234567A\n";

    public static final String MESSAGE_SUCCESS = "From person %s, successfully deleted medicine usage:\n%s";
    public static final String MESSAGE_INVALID_MEDICINE_USAGE_DISPLAYED_INDEX = "The medicine usage "
            + "index provided is invalid";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with NRIC %s not found";

    private final Index targetId;
    private final Nric nric;

    /**
     * Creates an DeleteMedicineUsageCommand to delete a medicine usage of
     * the person identified by {@code Nric} at index {@code Index}.
     */
    public DeleteMedicineUsageCommand(Nric nric, Index targetId) {
        requireNonNull(targetId);
        this.nric = nric;
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person person = model.findPersonByNric(nric);
        if (person == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, nric));
        }

        ObservableList<MedicineUsage> medicineUsageList = person.getMedicineUsages();

        if (targetId.getZeroBased() >= medicineUsageList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_MEDICINE_USAGE_DISPLAYED_INDEX));
        }

        MedicineUsage medUsageToDelete = medicineUsageList.get(targetId.getZeroBased());
        model.deleteMedicineUsage(person, medUsageToDelete);

        return new CommandResult(String.format(MESSAGE_SUCCESS, nric, medUsageToDelete.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteMedicineUsageCommand
                && nric.equals(((DeleteMedicineUsageCommand) other).nric));
    }
}
