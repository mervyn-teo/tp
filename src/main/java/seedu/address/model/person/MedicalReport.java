package seedu.address.model.person;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.medicineusage.MedicineUsage;
import seedu.address.model.medicineusage.MedicineUsageList;

/**
 * Represents a Person's medical report in the klinix.
 */
public class MedicalReport {

    public static final String MESSAGE_CONSTRAINTS =
            "Medical report should contain only letters, numbers, spaces, or hyphens, "
                    + "and at least one alphabetic character";

    public static final MedicalReport EMPTY_MEDICAL_REPORT =
            new MedicalReport("None", "None", "None", "None");

    public final String value;
    private final String allergens;
    private final String illnesses;
    private final String surgeries;
    private final String immunizations;
    private final MedicineUsageList medicineUsages;

    /**
     * Constructs a {@code MedicalReport} with the specified medical report details.
     *
     * @param allergens A string representing the allergens of the person.
     * @param illnesses A string representing the illnesses of the person.
     * @param surgeries A string representing the surgeries of the person.
     * @param immunizations A string representing the immunizations of the person.
     */
    public MedicalReport(String allergens, String illnesses, String surgeries, String immunizations) {
        this.allergens = allergens;
        this.illnesses = illnesses;
        this.surgeries = surgeries;
        this.immunizations = immunizations;
        this.medicineUsages = new MedicineUsageList();
        this.value = this.toString();
    }

    public String getAllergens() {
        return allergens;
    }

    public String getIllnesses() {
        return illnesses;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public String getImmunizations() {
        return immunizations;
    }

    public ObservableList<MedicineUsage> getMedicineUsages() {
        return medicineUsages.asUnmodifiableObservableList();
    }

    public void add(MedicineUsage toAdd) {
        medicineUsages.add(toAdd);
    }

    public void remove(MedicineUsage toRemove) {
        medicineUsages.remove(toRemove);
    }

    public void setMedicineUsages(List<MedicineUsage> newData) {
        medicineUsages.setMedicineUsages(newData);
    }

    public void reset() {
        medicineUsages.reset();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MedicalReport that = (MedicalReport) obj;
        return Objects.equals(allergens, that.allergens)
                && Objects.equals(illnesses, that.illnesses)
                && Objects.equals(surgeries, that.surgeries)
                && Objects.equals(immunizations, that.immunizations)
                && Objects.equals(medicineUsages, that.medicineUsages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allergens, illnesses, surgeries, immunizations, medicineUsages);
    }

    @Override
    public String toString() {
        return (
                "  ➤ Allergens: " + allergens + "\n"
                + "  ➤ Illnesses: " + illnesses + "\n"
                + "  ➤ Surgeries: " + surgeries + "\n"
                + "  ➤ Immunizations: " + immunizations
            );
    }

    /**
     * Returns true if a given string is a valid medical report field
     */
    public static boolean isValidMedicalField(String input) {
        return input != null
                && !input.trim().isEmpty()
                && input.matches("^[a-zA-Z0-9 ,\\-]+$")
                && input.matches(".*[a-zA-Z].*");
    }
}


