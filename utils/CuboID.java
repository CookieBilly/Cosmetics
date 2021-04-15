

package ws.billy.CookieGadgets.utils;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.block.Block;

public class CuboID implements Iterable<Block>, Cloneable, ConfigurationSerializable
{
    protected final String worldName;
    protected final int x1;
    protected final int y1;
    protected final int z1;
    protected final int x2;
    protected final int y2;
    protected final int z2;
    
    public CuboID(final Location location, final Location location2) {
        if (!location.getWorld().equals(location2.getWorld())) {
            throw new IllegalArgumentException("Locations must be on the same world");
        }
        this.worldName = location.getWorld().getName();
        this.x1 = Math.min(location.getBlockX(), location2.getBlockX());
        this.y1 = Math.min(location.getBlockY(), location2.getBlockY());
        this.z1 = Math.min(location.getBlockZ(), location2.getBlockZ());
        this.x2 = Math.max(location.getBlockX(), location2.getBlockX());
        this.y2 = Math.max(location.getBlockY(), location2.getBlockY());
        this.z2 = Math.max(location.getBlockZ(), location2.getBlockZ());
    }
    
    public CuboID(final Location location) {
        this(location, location);
    }
    
    public CuboID(final CuboID cuboID) {
        this(cuboID.getWorld().getName(), cuboID.x1, cuboID.y1, cuboID.z1, cuboID.x2, cuboID.y2, cuboID.z2);
    }
    
    public CuboID(final World world, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.worldName = world.getName();
        this.x1 = Math.min(n, n4);
        this.x2 = Math.max(n, n4);
        this.y1 = Math.min(n2, n5);
        this.y2 = Math.max(n2, n5);
        this.z1 = Math.min(n3, n6);
        this.z2 = Math.max(n3, n6);
    }
    
    private CuboID(final String worldName, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.worldName = worldName;
        this.x1 = Math.min(n, n4);
        this.x2 = Math.max(n, n4);
        this.y1 = Math.min(n2, n5);
        this.y2 = Math.max(n2, n5);
        this.z1 = Math.min(n3, n6);
        this.z2 = Math.max(n3, n6);
    }
    
    public CuboID(final Map<String, Object> map) {
        this.worldName = map.get("worldName");
        this.x1 = (int)map.get("x1");
        this.x2 = (int)map.get("x2");
        this.y1 = (int)map.get("y1");
        this.y2 = (int)map.get("y2");
        this.z1 = (int)map.get("z1");
        this.z2 = (int)map.get("z2");
    }
    
    public Map<String, Object> serialize() {
        final HashMap<String, String> hashMap = (HashMap<String, String>)new HashMap<String, Integer>();
        hashMap.put("worldName", (Integer)this.worldName);
        hashMap.put("x1", Integer.valueOf(this.x1));
        hashMap.put("y1", Integer.valueOf(this.y1));
        hashMap.put("z1", Integer.valueOf(this.z1));
        hashMap.put("x2", Integer.valueOf(this.x2));
        hashMap.put("y2", Integer.valueOf(this.y2));
        hashMap.put("z2", Integer.valueOf(this.z2));
        return (Map<String, Object>)hashMap;
    }
    
    public Location getLowerNE() {
        return new Location(this.getWorld(), (double)this.x1, (double)this.y1, (double)this.z1);
    }
    
    public Location getUpperSW() {
        return new Location(this.getWorld(), (double)this.x2, (double)this.y2, (double)this.z2);
    }
    
    public List<Block> getBlocks() {
        final Iterator<Block> iterator = this.iterator();
        final ArrayList<Block> list = new ArrayList<Block>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
    
    public Location getCenter() {
        return new Location(this.getWorld(), this.getLowerX() + (this.getUpperX() + 1 - this.getLowerX()) / 2.0, this.getLowerY() + (this.getUpperY() + 1 - this.getLowerY()) / 2.0, this.getLowerZ() + (this.getUpperZ() + 1 - this.getLowerZ()) / 2.0);
    }
    
    public World getWorld() {
        final World world = Bukkit.getWorld(this.worldName);
        if (world == null) {
            throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        }
        return world;
    }
    
    public int getSizeX() {
        return this.x2 - this.x1 + 1;
    }
    
    public int getSizeY() {
        return this.y2 - this.y1 + 1;
    }
    
    public int getSizeZ() {
        return this.z2 - this.z1 + 1;
    }
    
    public int getLowerX() {
        return this.x1;
    }
    
    public int getLowerY() {
        return this.y1;
    }
    
    public int getLowerZ() {
        return this.z1;
    }
    
    public int getUpperX() {
        return this.x2;
    }
    
    public int getUpperY() {
        return this.y2;
    }
    
    public int getUpperZ() {
        return this.z2;
    }
    
    public Block[] corners() {
        final Block[] array = new Block[8];
        final World world = this.getWorld();
        array[0] = world.getBlockAt(this.x1, this.y1, this.z1);
        array[1] = world.getBlockAt(this.x1, this.y1, this.z2);
        array[2] = world.getBlockAt(this.x1, this.y2, this.z1);
        array[3] = world.getBlockAt(this.x1, this.y2, this.z2);
        array[4] = world.getBlockAt(this.x2, this.y1, this.z1);
        array[5] = world.getBlockAt(this.x2, this.y1, this.z2);
        array[6] = world.getBlockAt(this.x2, this.y2, this.z1);
        array[7] = world.getBlockAt(this.x2, this.y2, this.z2);
        return array;
    }
    
    public CuboID expand(final CuboIDDirection obj, final int n) {
        switch (obj) {
            case North: {
                return new CuboID(this.worldName, this.x1 - n, this.y1, this.z1, this.x2, this.y2, this.z2);
            }
            case South: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2 + n, this.y2, this.z2);
            }
            case East: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1 - n, this.x2, this.y2, this.z2);
            }
            case West: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 + n);
            }
            case Down: {
                return new CuboID(this.worldName, this.x1, this.y1 - n, this.z1, this.x2, this.y2, this.z2);
            }
            case Up: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 + n, this.z2);
            }
            default: {
                throw new IllegalArgumentException("Invalid direction " + obj);
            }
        }
    }
    
    public CuboID shift(final CuboIDDirection cuboIDDirection, final int n) {
        return this.expand(cuboIDDirection, n).expand(cuboIDDirection.opposite(), -n);
    }
    
    public CuboID outset(final CuboIDDirection obj, final int n) {
        CuboID cuboID = null;
        switch (obj) {
            case Horizontal: {
                cuboID = this.expand(CuboIDDirection.North, n).expand(CuboIDDirection.South, n).expand(CuboIDDirection.East, n).expand(CuboIDDirection.West, n);
                break;
            }
            case Vertical: {
                cuboID = this.expand(CuboIDDirection.Down, n).expand(CuboIDDirection.Up, n);
                break;
            }
            case Both: {
                cuboID = this.outset(CuboIDDirection.Horizontal, n).outset(CuboIDDirection.Vertical, n);
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid direction " + obj);
            }
        }
        return cuboID;
    }
    
    public CuboID inset(final CuboIDDirection cuboIDDirection, final int n) {
        return this.outset(cuboIDDirection, -n);
    }
    
    public boolean contains(final int n, final int n2, final int n3) {
        return n >= this.x1 && n <= this.x2 && n2 >= this.y1 && n2 <= this.y2 && n3 >= this.z1 && n3 <= this.z2;
    }
    
    public boolean contains(final Block block) {
        return this.contains(block.getLocation());
    }
    
    public boolean contains(final Location location) {
        return this.worldName.equals(location.getWorld().getName()) && this.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
    
    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }
    
    public byte getAverageLightLevel() {
        long n = 0L;
        int n2 = 0;
        for (final Block block : this) {
            if (block.isEmpty()) {
                n += block.getLightLevel();
                ++n2;
            }
        }
        return (byte)((n2 > 0) ? ((byte)(n / n2)) : 0);
    }
    
    public CuboID contract() {
        return this.contract(CuboIDDirection.Down).contract(CuboIDDirection.South).contract(CuboIDDirection.East).contract(CuboIDDirection.Up).contract(CuboIDDirection.North).contract(CuboIDDirection.West);
    }
    
    public CuboID contract(final CuboIDDirection obj) {
        CuboID cuboID = this.getFace(obj.opposite());
        switch (obj) {
            case Down: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getLowerY() > this.getLowerY()) {
                    cuboID = cuboID.shift(CuboIDDirection.Down, 1);
                }
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, cuboID.getUpperY(), this.z2);
            }
            case Up: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getUpperY() < this.getUpperY()) {
                    cuboID = cuboID.shift(CuboIDDirection.Up, 1);
                }
                return new CuboID(this.worldName, this.x1, cuboID.getLowerY(), this.z1, this.x2, this.y2, this.z2);
            }
            case North: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getLowerX() > this.getLowerX()) {
                    cuboID = cuboID.shift(CuboIDDirection.North, 1);
                }
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, cuboID.getUpperX(), this.y2, this.z2);
            }
            case South: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getUpperX() < this.getUpperX()) {
                    cuboID = cuboID.shift(CuboIDDirection.South, 1);
                }
                return new CuboID(this.worldName, cuboID.getLowerX(), this.y1, this.z1, this.x2, this.y2, this.z2);
            }
            case East: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getLowerZ() > this.getLowerZ()) {
                    cuboID = cuboID.shift(CuboIDDirection.East, 1);
                }
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, cuboID.getUpperZ());
            }
            case West: {
                while (cuboID.containsOnly(Material.AIR) && cuboID.getUpperZ() < this.getUpperZ()) {
                    cuboID = cuboID.shift(CuboIDDirection.West, 1);
                }
                return new CuboID(this.worldName, this.x1, this.y1, cuboID.getLowerZ(), this.x2, this.y2, this.z2);
            }
            default: {
                throw new IllegalArgumentException("Invalid direction " + obj);
            }
        }
    }
    
    public CuboID getFace(final CuboIDDirection obj) {
        switch (obj) {
            case Down: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);
            }
            case Up: {
                return new CuboID(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);
            }
            case North: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);
            }
            case South: {
                return new CuboID(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);
            }
            case East: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);
            }
            case West: {
                return new CuboID(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);
            }
            default: {
                throw new IllegalArgumentException("Invalid direction " + obj);
            }
        }
    }
    
    public boolean containsOnly(final Material material) {
        final Iterator<Block> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getType() != material) {
                return false;
            }
        }
        return true;
    }
    
    public CuboID getBoundingCuboID(final CuboID cuboID) {
        if (cuboID == null) {
            return this;
        }
        return new CuboID(this.worldName, Math.min(this.getLowerX(), cuboID.getLowerX()), Math.min(this.getLowerY(), cuboID.getLowerY()), Math.min(this.getLowerZ(), cuboID.getLowerZ()), Math.max(this.getUpperX(), cuboID.getUpperX()), Math.max(this.getUpperY(), cuboID.getUpperY()), Math.max(this.getUpperZ(), cuboID.getUpperZ()));
    }
    
    public Block getRelativeBlock(final int n, final int n2, final int n3) {
        return this.getWorld().getBlockAt(this.x1 + n, this.y1 + n2, this.z1 + n3);
    }
    
    public Block getRelativeBlock(final World world, final int n, final int n2, final int n3) {
        return world.getBlockAt(this.x1 + n, this.y1 + n2, this.z1 + n3);
    }
    
    public List<Chunk> getChunks() {
        final ArrayList<Chunk> list = new ArrayList<Chunk>();
        final World world = this.getWorld();
        final int n = this.getLowerX() & 0xFFFFFFF0;
        final int n2 = this.getUpperX() & 0xFFFFFFF0;
        final int n3 = this.getLowerZ() & 0xFFFFFFF0;
        final int n4 = this.getUpperZ() & 0xFFFFFFF0;
        for (int i = n; i <= n2; i += 16) {
            for (int j = n3; j <= n4; j += 16) {
                list.add(world.getChunkAt(i >> 4, j >> 4));
            }
        }
        return list;
    }
    
    @Override
    public Iterator<Block> iterator() {
        return new CuboIDIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }
    
    public CuboID clone() {
        return new CuboID(this);
    }
    
    @Override
    public String toString() {
        return new String("CuboID: " + this.worldName + "," + this.x1 + "," + this.y1 + "," + this.z1 + "=>" + this.x2 + "," + this.y2 + "," + this.z2);
    }
    
    public boolean isEmpty() {
        for (final Block block : this.getBlocks()) {
            if (block.getType() != Material.AIR) {
                return false;
            }
            if (BlockUtil.hasHangingEntities(block.getLocation())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean hasHangingEntities() {
        final Iterator<Block> iterator = this.getBlocks().iterator();
        while (iterator.hasNext()) {
            if (BlockUtil.hasHangingEntities(iterator.next().getLocation())) {
                return true;
            }
        }
        return false;
    }
    
    public enum CuboIDDirection
    {
        North("North", 0), 
        East("East", 1), 
        South("South", 2), 
        West("West", 3), 
        Up("Up", 4), 
        Down("Down", 5), 
        Horizontal("Horizontal", 6), 
        Vertical("Vertical", 7), 
        Both("Both", 8), 
        Unknown("Unknown", 9);
        
        private CuboIDDirection(final String name, final int ordinal) {
        }
        
        public CuboIDDirection opposite() {
            switch (this) {
                case North: {
                    return CuboIDDirection.South;
                }
                case East: {
                    return CuboIDDirection.West;
                }
                case South: {
                    return CuboIDDirection.North;
                }
                case West: {
                    return CuboIDDirection.East;
                }
                case Horizontal: {
                    return CuboIDDirection.Vertical;
                }
                case Vertical: {
                    return CuboIDDirection.Horizontal;
                }
                case Up: {
                    return CuboIDDirection.Down;
                }
                case Down: {
                    return CuboIDDirection.Up;
                }
                case Both: {
                    return CuboIDDirection.Both;
                }
                default: {
                    return CuboIDDirection.Unknown;
                }
            }
        }
    }
    
    public class CuboIDIterator implements Iterator<Block>
    {
        private World w;
        private int baseX;
        private int baseY;
        private int baseZ;
        private int x;
        private int y;
        private int z;
        private int sizeX;
        private int sizeY;
        private int sizeZ;
        
        public CuboIDIterator(final World w, final int baseX, final int baseY, final int baseZ, final int n, final int n2, final int n3) {
            this.w = w;
            this.baseX = baseX;
            this.baseY = baseY;
            this.baseZ = baseZ;
            this.sizeX = Math.abs(n - baseX) + 1;
            this.sizeY = Math.abs(n2 - baseY) + 1;
            this.sizeZ = Math.abs(n3 - baseZ) + 1;
            final int x = 0;
            this.z = x;
            this.y = x;
            this.x = x;
        }
        
        @Override
        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }
        
        @Override
        public Block next() {
            final Block block = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++this.x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }
            return block;
        }
        
        @Override
        public void remove() {
        }
    }
}
