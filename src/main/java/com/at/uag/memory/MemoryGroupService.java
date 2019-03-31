package com.at.uag.memory;

import com.at.uag.Exceptions.GroupCreationException;
import com.at.uag.Exceptions.GroupNotFoundException;
import com.at.uag.api.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemoryGroupService
{
    private final List<Group> groupList;

    public MemoryGroupService(List<Group> groupList)
    {
        this.groupList = groupList;
    }

    /**
     *
     * @param name
     * @return
     * @throws GroupCreationException
     */
    public Group createGroup(String name) throws GroupCreationException
    {
        if(groupList.parallelStream().noneMatch(grp -> grp.getGroupName().equalsIgnoreCase(name)))
        {
            Group newGroup = new Group(UUID.randomUUID(), name);
            groupList.add(newGroup);
            return newGroup;
        }

        throw new GroupCreationException("Group already exists");
    }

    /**
     * Remove group by name
     * @param name
     * @throws GroupNotFoundException
     */
    public void removeGroup(String name) throws GroupNotFoundException
    {
        if(groupList.parallelStream().noneMatch(grp -> grp.getGroupName().equalsIgnoreCase(name)))
        {
            groupList.add(new Group(UUID.randomUUID(),name));
        }
        throw new GroupNotFoundException("Group does not exist");
    }

    /**
     *
     * @param uuid
     * @throws GroupNotFoundException
     */
    public void removeGroup(UUID uuid) throws GroupNotFoundException
    {
        Optional<Group> groupToRemove = groupList.parallelStream().filter(grp -> grp.getGroupId().equals(uuid)).findAny();

        groupToRemove.ifPresent(groupList::remove);

        //Todo: clean up the service
    }

    public Group findGroup(UUID groupID) throws GroupNotFoundException
    {
        Optional<Group> optGroup = groupList.parallelStream().filter(grp -> grp.getGroupId().equals(groupID)).findAny();

        if(optGroup.isPresent())
            return optGroup.get();

        throw new GroupNotFoundException("Group Data not Found");
    }


    public List<Group> getGroupList() {
        return groupList;
    }
}
