package com.mccraftaholics.warpportals.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mccraftaholics.warpportals.helpers.Regex;
import com.mccraftaholics.warpportals.helpers.Utils;
import com.mccraftaholics.warpportals.helpers.persistance.PortalPersistenceLayer;
import com.mccraftaholics.warpportals.helpers.persistance.WarpPortalPersistedData;
import com.mccraftaholics.warpportals.helpers.persistance.WorldTypeAdapter;
import com.mccraftaholics.warpportals.manager.old.OldPersistenceManager;
import com.mccraftaholics.warpportals.objects.Coords;
import com.mccraftaholics.warpportals.objects.CoordsPY;
import com.mccraftaholics.warpportals.objects.DestinationInfo;
import com.mccraftaholics.warpportals.objects.PortalInfo;
import junit.framework.Assert;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Bukkit.class, UUID.class})
public class PersistanceManagerTest {
    Logger mockedLogger;
    UUID mockedUuid;
    String portalData0602;
    String portalData0602Zeroed;
    WarpPortalPersistedData handWrittenDataObj;

    @Before
    public void setup() {
        try {
            // Setup mocks
            mockedLogger = mock(Logger.class);
            PowerMockito.mockStatic(Bukkit.class);
            World world = mock(World.class);
            mockedUuid = PowerMockito.mock(UUID.class);
            Mockito.when(world.getUID()).thenReturn(mockedUuid);
            Mockito.when(world.getName()).thenReturn("world");
            Block mockedBlock = Mockito.mock(Block.class);
            Mockito.when(mockedBlock.getType()).thenReturn(Material.PORTAL);
            Mockito.when(world.getBlockAt(Mockito.any(Location.class))).thenReturn(mockedBlock);
            Mockito.when(Bukkit.getWorld(Mockito.any(UUID.class))).thenReturn(world);
            Mockito.when(Bukkit.getWorld("world")).thenReturn(world);

            // Prepare latest string for comparison
            portalData0602 = Utils.readStream(getClass().getClassLoader().getResourceAsStream("portals.v0601.yml"), "UTF-8");
            portalData0602Zeroed = portalData0602.replaceAll(Regex.IS_UUID, "00000000-0000-0000-0000-000000000000");

            // Example portal data
            handWrittenDataObj = new WarpPortalPersistedData();
            handWrittenDataObj.portals.add(
                    new PortalInfo(
                            UUID.fromString("11111111-1111-1111-1111-111111111111"),
                            "Test1",
                            Material.getMaterial("PORTAL"),
                            "$default",
                            Arrays.asList(
                                    new Coords(Bukkit.getWorld("world"), 173.0, 65.0, 250.0),
                                    new Coords(Bukkit.getWorld("world"), 173.0, 64.0, 250.0)
                            ),
                            new CoordsPY(Bukkit.getWorld("world"), 168.5, 66.0, 264.9, 7.9f, -1.3f)
                    ));
            handWrittenDataObj.portals.add(
                    new PortalInfo(
                            UUID.fromString("22222222-2222-2222-2222-222222222222"),
                            "Test2",
                            Material.getMaterial("PORTAL"),
                            "$default",
                            Arrays.asList(
                                    new Coords(Bukkit.getWorld("world"), 168.0, 67.0, 268.0),
                                    new Coords(Bukkit.getWorld("world"), 167.0, 67.0, 268.0)
                            ),
                            new CoordsPY(Bukkit.getWorld("world"), 174.4, 64.0, 254.5, 2.7f, -178.5f)
                    ));
            handWrittenDataObj.destinations.add(
                    new DestinationInfo(
                            "Dest1",
                            new CoordsPY(Bukkit.getWorld("world"), 174.4, 64.0, 254.5, 2.7f, -178.5f)
                    ));
            handWrittenDataObj.destinations.add(
                    new DestinationInfo(
                            "Dest2",
                            new CoordsPY(Bukkit.getWorld("world"), 168.5, 66.0, 264.9, 7.9f, -1.3f)
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean testPersistedDataEquals(WarpPortalPersistedData a, WarpPortalPersistedData b) {
        if (a == b) return true;

        if (a.destinations != null ? !a.destinations.equals(b.destinations) : b.destinations != null)
            return false;
        if (a.portals != null && b.portals != null) {
            Set<PortalInfo> aPortals = new HashSet<PortalInfo>(a.portals);
            Set<PortalInfo> bPortals = new HashSet<PortalInfo>(b.portals);
            if (!a.portals.equals(b.portals)) return false;
        } else {
            return false;
        }

        return true;
    }

    @Test
    public void outputGson() {
        PortalPersistenceLayer persistenceLayer = new PortalPersistenceLayer();
        System.out.println(persistenceLayer.serialize(handWrittenDataObj));
    }

    @Test
    public void test0602MatchHandWritten() {
        PortalPersistenceLayer persistenceLayer = new PortalPersistenceLayer();
        WarpPortalPersistedData parsed0602Data = persistenceLayer.deserialize(portalData0602);

        Assert.assertTrue(testPersistedDataEquals(parsed0602Data, handWrittenDataObj));
    }

    @Test
    public void testMigrateFrom0601ToModern() {
        String portalData0601;
        try {
            portalData0601 = Utils.readStream(getClass().getClassLoader().getResourceAsStream("portals.v0601.yml"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        OldPersistenceManager.PersistedData oldData = OldPersistenceManager.parseDataFile(portalData0601, mockedLogger);


        Assert.assertTrue(testPersistedDataEquals(oldData.toModernFormat(), handWrittenDataObj));
    }
}
