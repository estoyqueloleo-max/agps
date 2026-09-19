package com.giobat.AgpsTrackerPP;

import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class IntServLocGpsPPTest {

    @Test
    public void testServiceTaskRemovedStopsWhenNotRecording() {
        AgpsApplication.f3568j = false; // Not recording

        ServiceController<IntServLocGpsPP> controller = Robolectric.buildService(IntServLocGpsPP.class);
        IntServLocGpsPP service = controller.get();

        // Simulate onTaskRemoved
        service.onTaskRemoved(new Intent());

        // Service should not be tracking
        Assert.assertFalse("Service flag A should be false after destruction", IntServLocGpsPP.A);
    }
}
