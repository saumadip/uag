package com.at.uag.api;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class Group
{
    private  List<User> usersInGroup;

    private UUID groupId;

    private  String groupName;

    private  Map<Perms,List<User>> permMap;


    public Group(UUID groupId, String groupName)
    {
        this.groupId = groupId;
        this.groupName = groupName;
        this.permMap = new ConcurrentHashMap<>();
        this.usersInGroup = new CopyOnWriteArrayList<>();
    }


    /**
     * Get groupID
     * @return
     */
    public UUID getGroupId()
    {
        return groupId;
    }

    /**
     *
     * @return
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     *
     * @return
     */
    public List<User> getUsersInGroup()
    {
        return usersInGroup;
    }

    /**
     *
     * @return
     */
    public Map<Perms, List<User>> getPermMap()
    {
        return permMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (!getUsersInGroup().equals(group.getUsersInGroup())) return false;
        if (!getGroupId().equals(group.getGroupId())) return false;
        if (!getGroupName().equals(group.getGroupName())) return false;
        return getPermMap().equals(group.getPermMap());
    }

    @Override
    public int hashCode() {
        int result = getUsersInGroup().hashCode();
        result = 31 * result + getGroupId().hashCode();
        result = 31 * result + getGroupName().hashCode();
        result = 31 * result + getPermMap().hashCode();
        return result;
    }
}
