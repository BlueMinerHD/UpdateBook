package de.BlueMiner_HD.UpdateBook.API;

//import de.BlueMiner_HD.UpdateBook.Methoden.Files;
import de.BlueMiner_HD.UpdateBook.main;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


public class BlueAPI {

    /*
     * This contains many API's which allows you to develop your Plugins way easier.
     * Activate Bungeecord in your main! Like this:
     * this.getServer().getMessenger().registerOutgoingPluginChannel(this,
     * "BungeeCord"); - not needed here
     */

    public static ItemStack createItemwithMaterial(Material m, int subid, int amount, String DisplayName,
                                                   List<String> lore) {
        ItemStack item = new ItemStack(m, amount, (short) subid);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(DisplayName);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;

    }

    public static ItemStack createItemwithID(int id, int subid, int amount, String DisplayName, List<String> lore) {

        @SuppressWarnings("deprecation")
        ItemStack is = new ItemStack(id, amount, (short) subid);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(DisplayName);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;

    }

    public static ItemStack createHead(String owner, ArrayList<String> lore, String name) {

        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta sm = (SkullMeta) i.getItemMeta();
        sm.setOwner(owner);
        sm.setDisplayName(name);
        sm.setLore(lore);
        i.setItemMeta(sm);
        return i;
    }

    public static int getRandom(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getNumber(String number) {
        if (isNumber(number)) {
            int i = Integer.parseInt(number);
            return i;
        }
        return 0;
    }

    public static String getNamebyUUID(UUID uuid) {
        try {
            return Bukkit.getPlayer(uuid).getName();
        } catch (Exception e) {
            return "";
        }
    }

    public static UUID getUUIDbyName(String name) {
        try {
            return Bukkit.getPlayer(name).getUniqueId();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getRandomString(List<String> list) {
        int rn = getRandom(list.size());
        return list.get(rn);
    }

    public static Player getRandomPlayer(List<Player> list) {
        int rn = getRandom(list.size());
        return list.get(rn);
    }

    public static Object getRandomObject(List<Object> list) {
        int rn = getRandom(list.size());
        return list.get(rn);
    }

    public static void connect(Player player, String servername) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(servername);
        } catch (IOException e) {
        }

        player.sendPluginMessage(main.getInstance(), "BungeeCord", b.toByteArray());
    }

    public static void hidePlayer(Player player) {
        for (Player player2 : Bukkit.getOnlinePlayers()) {
            player2.hidePlayer(player);
        }
    }

    public static void showPlayer(Player player) {
        for (Player player2 : Bukkit.getOnlinePlayers()) {
            player2.showPlayer(player);
        }
    }

    public static void hidePlayertoIngames(Player player, List<Player> spectators) {
        for (Player player2 : Bukkit.getOnlinePlayers()) {
            if (spectators.contains(player2) == false) {
                player2.hidePlayer(player);
            }
        }
    }

    public static void showPlayertoIngames(Player player, List<Player> spectators) {
        for (Player player2 : Bukkit.getOnlinePlayers()) {
            if (spectators.contains(player2) == false) {
                player2.showPlayer(player);
            }
        }
    }

    public static void createNewFile(String filename) {

        main.getInstance().getDataFolder().mkdir();
        File file = new File(main.getInstance().getDataFolder().getPath(), filename);
        if (file.exists())
            return;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String filename) {

        File file = new File(main.getInstance().getDataFolder().getPath(), filename);
        return file;

    }

    public static void deleteFile(String filename) {

        File file = new File(main.getInstance().getDataFolder().getPath(), filename);
        file.delete();

    }

    public static YamlConfiguration getConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveFile(File file, YamlConfiguration yml) {
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Location getLocation(String filename, String name) {

        try {
            YamlConfiguration yml = getConfiguration(getFile(filename));

            double x = yml.getDouble(name + ".x");
            double y = yml.getDouble(name + ".y");
            double z = yml.getDouble(name + ".z");
            float yaw = yml.getLong(name + ".yaw");
            float pitch = yml.getLong(name + ".pitch");
            World world = Bukkit.getWorld(yml.getString(name + ".world"));

            Location loc = new Location(world, x, y, z, yaw, pitch);
            return loc;
        } catch (Exception e) {
            return null;
        }
    }

    public static void saveLocation(Location loc, String filename, String name) {

        YamlConfiguration yml = getConfiguration(getFile(filename));

        if (loc == null) {

            yml.set(name + ".x", null);
            yml.set(name + ".y", null);
            yml.set(name + ".z", null);
            yml.set(name + ".yaw", null);
            yml.set(name + ".pitch", null);
            yml.set(name + ".world", null);

            saveFile(getFile(filename), yml);

            return;
        }

        yml.set(name + ".x", loc.getX());
        yml.set(name + ".y", loc.getY());
        yml.set(name + ".z", loc.getZ());
        yml.set(name + ".yaw", loc.getYaw());
        yml.set(name + ".pitch", loc.getPitch());
        yml.set(name + ".world", loc.getWorld().getName());

        saveFile(getFile(filename), yml);

        //Files.loadLocations();

    }

    public static FlyingItem spawnFlyingItem(ItemStack item, Location location) {

        FlyingItem fly = new FlyingItem();

        fly.setLocation(location);
        fly.setHeight(2.25);
        fly.setItemStack(item);
        fly.spawn();

        return fly;
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", new Class[]{getNMSClass("Packet")})
                    .invoke(playerConnection, new Object[]{packet});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static void sendTitle(Player player, String title, String subtitle) {

        player.sendTitle(title, subtitle);

    }

    public static void sendActionbar(String msg, Player player) {
        msg = msg.replace("&", "§");
        msg = msg.replace("%p", player.getDisplayName());

        PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;

        IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
        con.sendPacket(packet);
    }

    public void sendTabTitle(Player player, String header, String footer) {
        if (header == null)
            header = "";

        if (footer == null)
            footer = "";

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");

        IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

        try {

            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            connection.sendPacket(headerPacket);

        }
    }

    static HashMap<Player, Hologram> holo = new HashMap<>();

    public static void setHolo(Player player, String[] text, Location loc, boolean clearlast) {
        Hologram hologram = new Hologram(text, loc);
        hologram.showPlayer(player);
        if (clearlast) {
            if (holo.containsKey(player)) {
                Hologram holotoclear = holo.get(player);
                holotoclear.hidePlayer(player);
                holo.remove(player);
            }
        }
        holo.put(player, hologram);
    }

    public static void removeHolo(Player player) {
        if (holo.containsKey(player)) {
            Hologram hologram = holo.get(player);
            hologram.hidePlayer(player);
            holo.remove(player);
        }
    }

    public static void clearChat(Player p, int size) {

        for (int i = 0; i <= size; i++) {
            p.sendMessage(" ");
        }

    }
}

class FlyingItem {

    private ArmorStand armorstand;
    private Location location;
    private String text = null;
    private Boolean h = false;
    private ItemStack itemstack;
    private double height = -1.3;

    public FlyingItem() {

    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setItemStack(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public void setHeight(double height) {
        this.height = height - 1.3;
        if (this.location != null) {
            this.location.setY(this.location.getY() + this.height);
            h = true;
        }
    }

    public void remove() {
        this.location = null;
        this.armorstand.remove();
        this.armorstand.getPassenger().remove();
        this.armorstand = null;
        this.h = false;
        this.height = 0;
        this.text = null;
        this.itemstack = null;
    }

    public void teleport(Location location) {
        if (this.location != null) {
            armorstand.teleport(location);
            this.location = location;
        }
    }

    public void spawn() {
        if (!h) {
            this.location.setY(this.location.getY() + this.height);
            h = true;
        }
        armorstand = (ArmorStand) this.location.getWorld().spawn(this.location, ArmorStand.class);
        armorstand.setGravity(false);
        armorstand.setVisible(false);
        Item i = this.location.getWorld().dropItem(this.getLocation().add(0, 2, 0), this.itemstack);
        i.setPickupDelay(2147483647);
        if (this.text != null) {
            i.setCustomName(this.text);
            i.setCustomNameVisible(true);
        }
        armorstand.setPassenger(i);
    }

    public Location getLocation() {
        return this.location;
    }

    public ItemStack getItemStack() {
        return this.itemstack;
    }

    public double getHeight() {
        return this.height;
    }

    public String getText() {
        return this.text;
    }
}

class Hologram {

    private java.util.List<EntityArmorStand> List = new ArrayList<EntityArmorStand>();
    private String[] text;
    private Location loc;
    private double distance = 0.25D;
    int count;

    public Hologram(String[] text, Location loc) {

        this.text = text;
        this.loc = loc;
        create();
    }

    public void create() {
        for (String text : this.text) {
            if (text != null) {

                EntityArmorStand e = new EntityArmorStand(((CraftWorld) this.loc.getWorld()).getHandle(),
                        this.loc.getX(), this.loc.getY(), this.loc.getZ());

                e.setCustomName(text);
                e.setCustomNameVisible(true);
                e.setInvisible(true);
                e.setGravity(true);
                List.add(e);
                count++;
                this.loc.subtract(0, this.distance, 0);

            }
        }
        for (int i = 0; i < count; i++) {
            this.loc.add(0, this.distance, 0);
        }
        this.count++;
    }

    public void hidePlayer(Player p) {
        for (EntityArmorStand a : List) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(a.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

        }

    }

    public void showPlayer(Player p) {
        for (EntityArmorStand a : List) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(a);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

        }

    }

}
