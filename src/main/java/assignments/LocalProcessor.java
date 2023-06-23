package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    public void setStringsAndPrintListHashCode(List<String> strings) {
        strings = new LinkedList<>(strings);
        for (int i = 0; i < period; i++) {
            System.out.println(strings.get(i).hashCode());
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String buildFullProcessorName(List<String> stringList) {
        StringJoiner builder = new StringJoiner(" ");
        builder.add(processorName);
        stringArrayList.forEach(builder::add);
        return builder.toString();
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorVersion(File file) throws FileNotFoundException {
        informationScanner = new Scanner(file);
        StringBuilder builder = new StringBuilder(processorVersion);
        while (informationScanner.hasNext()) {
            builder.append(informationScanner.nextLine());
        }
        processorVersion = builder.toString();
    }
}