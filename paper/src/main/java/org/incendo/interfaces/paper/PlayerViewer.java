package org.incendo.interfaces.paper;

import org.incendo.interfaces.core.view.InterfaceView;
import org.incendo.interfaces.core.view.InterfaceViewer;
import org.incendo.interfaces.paper.pane.ChestPane;
import org.incendo.interfaces.paper.view.BookView;
import org.incendo.interfaces.paper.view.ChestView;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * An interface viewer holding a {@link Player}.
 * <p>
 * Supports {@link ChestPane}.
 */
public final class PlayerViewer implements InterfaceViewer {

    /**
     * Returns a new {@code PlayerViewer} containging {@code player}.
     *
     * @param player the player
     * @return the viewer
     */
    public static @NonNull PlayerViewer of(final @NonNull Player player) {
        return new PlayerViewer(player);
    }

    private final @NonNull Player player;

    /**
     * Constructs {@code PlayerViewer}.
     *
     * @param player the player
     */
    private PlayerViewer(final @NonNull Player player) {
        this.player = player;
    }

    /**
     * Opens a pane for the viewer.
     *
     * @param pane the pane
     * @throws UnsupportedOperationException when the pane cannot be opened
     */
    @Override
    public void open(final @NonNull InterfaceView<?, ?, ?> pane) {
        if (pane instanceof ChestView) {
            this.openChestView((ChestView) pane);
            return;
        }

        if (pane instanceof BookView) {
            this.openBookView((BookView) pane);
            return;
        }

        throw new UnsupportedOperationException("Cannot open view of type " + pane.getClass().getName() + ".");
    }

    /**
     * Opens a chest pane.
     *
     * @param chestView the chest view
     */
    private void openChestView(final @NonNull ChestView chestView) {
        this.player.openInventory(chestView.inventory());
    }

    /**
     * Opens a book pane.
     *
     * @param bookView the book view
     */
    private void openBookView(final @NonNull BookView bookView) {
        this.player.openBook(bookView.book());
    }

    /**
     * Returns the player.
     *
     * @return the player
     */
    public @NonNull Player player() {
        return this.player;
    }

}
