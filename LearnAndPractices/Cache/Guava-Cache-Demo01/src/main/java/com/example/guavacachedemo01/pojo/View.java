package com.example.guavacachedemo01.pojo;

public class View {
    //A view is defined, just define it as an interface, no need to implement it
    public interface Summary {}

    //The view of SummaryWithRecipients is defined. Note that this view extends Summary. So this view contains the Summary view.
    // That is, when this view is output, the Summary view is also output.
    public interface SummaryWithRecipients extends Summary {}
}
