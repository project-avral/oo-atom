package oo.atom.codegen.stage;

import oo.atom.banner.BnnrFromResource;

public class AggroInstrumentationStage extends SequenceStage {
    public AggroInstrumentationStage() {
        super(
                new ShowBannerStage(
                        new BnnrFromResource(
                                "banner_aggro"
                        )
                ),
                new ShowStatsStage(),
                new BcelStage(
                        // @todo #99 In aggro mode, instrumentation must search for redundant object allocations and eliminate them
                        new oo.atom.codegen.bcel.plugin.VerbosePlugin(
                                new oo.atom.codegen.bcel.plugin.NopPlugin()
                        )
                )
        );
    }
}
