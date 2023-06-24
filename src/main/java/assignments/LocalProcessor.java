package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {

    private String processorName;
    private Long period = 10_000_000_000_000L;
    private String processorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private List<String> stringArrayList = new ArrayList<>();

    public LocalProcessor(
        String processorName,
        Long period,
        String processorVersion,
        Integer valueOfCheap,
        Scanner informationScanner,
        List<String> stringArrayList
    ) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
        // empty
    }

    @ListIteratorAnnotation
    public void printListElementsHashCode(List<String> strings) {
        strings.stream().filter(Objects::nonNull)
                .mapToInt(s -> s.hashCode())
                .forEach(System.out::println);
    }

    @FullNameProcessorGeneratorAnnotation
    public String buildFullProcessorName(List<String> stringList) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(processorName);
        stringArrayList.forEach(joiner::add);
        return joiner.toString();
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorVersion(File file) {
        StringBuilder builder = new StringBuilder(processorVersion);
        try {
            informationScanner = new Scanner(file);
            while (informationScanner.hasNext()) {
                builder.append(informationScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } finally {
            if (informationScanner != null) {
                informationScanner.close();
            }
        }
        processorVersion = builder.toString();
    }
}